package com.spring.common.util;

import com.jcraft.jsch.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SftpUtil
{
    private static final String SESSIONCONFIG = "StrictHostKeyChecking";
    private static final String SESSIONCONFIGVALUE = "no";
    private static final String SFTPCHANNELNAME = "sftp";
    private static final String EXECCHANNELNAME = "exec";

    private static final String ENCODING = "UTF-8";
    private static final String PROFILE = "/";
    private static final String PROFILEPOINT = ".";
    private static final String PROFILEPOINTS = "..";

    private static final Map<String, Map<ChannelExec, Session>> channelMap = new ConcurrentHashMap<String, Map<ChannelExec, Session>>();

    private static String serverIp;
    private static int serverPort;
    private static String serverName;
    private static String serverPwd;
    private static int timeout = 60000;

    private static Session session;
    private static Channel channel;
    private static ChannelSftp sftp;

    public SftpUtil(String serverIp, int serverPort, String serverName, String serverPwd)
    {
        SftpUtil.serverIp = serverIp;
        SftpUtil.serverPort = serverPort;
        SftpUtil.serverName = serverName;
        SftpUtil.serverPwd = serverPwd;
    }

    private static void init() throws Exception
    {
        if (sftp == null)
        {
            sftp = getChannel();
        }
        sftp.setFilenameEncoding(ENCODING);
    }

    private static ChannelSftp getChannel() throws JSchException
    {
        session = getJSchSession();
        channel = session.openChannel(SFTPCHANNELNAME);
        channel.connect();
        return (ChannelSftp) channel;
    }

    public static Session getJSchSession() throws JSchException
    {
        JSch jsch = new JSch();
        Session session = jsch.getSession(serverName, serverIp, serverPort);
        session.setPassword(serverPwd);
        session.setConfig(SESSIONCONFIG, SESSIONCONFIGVALUE);
        session.setTimeout(timeout);
        session.connect();
        return session;
    }

    private static void closeServer() throws Exception
    {
        if (channel != null)
        {
            channel.disconnect();
        }
        if (session != null)
        {
            session.disconnect();
        }
        if (sftp != null)
        {
            sftp.quit();
            sftp.disconnect();
            sftp = null;
        }
    }

    private static Vector<?> listFiles(String sftpDst) throws Exception
    {
        init();
        return sftp.ls(sftpDst);
    }

    private static boolean isDirExist(String sftpDst)
    {
        boolean isDirExistFlag = false;
        try
        {
            SftpATTRS sftpATTRS = sftp.lstat(sftpDst);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e)
        {
            isDirExistFlag = false;
        }
        return isDirExistFlag;
    }

    private static boolean createDir(String createpath)
    {
        try
        {
            if (isDirExist(createpath))
            {
                sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split(PROFILE);
            StringBuffer filePath = new StringBuffer(PROFILE);
            for (String path : pathArry)
            {
                if ("".equals(path))
                {
                    continue;
                }
                filePath.append(path + PROFILE);
                if (isDirExist(filePath.toString()))
                {
                    sftp.cd(filePath.toString());
                } else
                {
                    sftp.mkdir(filePath.toString());
                    sftp.cd(filePath.toString());
                }

            }
            sftp.cd(createpath);
            return true;
        } catch (SftpException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String getFilePath(String serverFile)
    {
        return serverFile.substring(0, serverFile.lastIndexOf(PROFILE));
    }

    public static void uploadFile(String localFile, String serverFile)
    {
        try
        {
            init();
            if (createDir(getFilePath(serverFile)))
            {
                sftp.put(localFile, serverFile, ChannelSftp.OVERWRITE);
            }
            closeServer();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void downloadFile(String localFile, String serverFile)
    {
        try
        {
            init();
            sftp.cd(getFilePath(serverFile));
            File toFile = new File(localFile);
            if (!toFile.exists())
            {
                toFile.mkdirs();
            }
            sftp.get(serverFile, localFile);
            closeServer();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String serverFile)
    {
        try
        {
            init();
            sftp.cd(getFilePath(serverFile));
            sftp.rm(serverFile);
            closeServer();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getServerFileNames(String serverFilePath)
    {
        List<String> serverNames = null;
        try
        {
            Vector<?> vectors = listFiles(getFilePath(serverFilePath));
            Iterator<?> iterators = vectors.iterator();
            serverNames = new ArrayList<String>();
            while (iterators.hasNext())
            {
                String fileName = ((ChannelSftp.LsEntry) iterators.next()).getFilename();
                if ((PROFILEPOINT.equals(fileName)) || (PROFILEPOINTS.equals(fileName)))
                {
                    continue;
                }
                serverNames.add(fileName);
            }
            closeServer();
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        return serverNames;
    }

    public static void execCommand(String channelKey, String command)
    {
        try
        {
            Session session = getJSchSession();
            ChannelExec exec = (ChannelExec) session.openChannel(EXECCHANNELNAME);
            exec.setCommand(command);
            exec.setErrStream(System.err);
            exec.connect();
            Map<ChannelExec, Session> channelSessionMap = new HashMap<ChannelExec, Session>();
            channelSessionMap.put(exec, session);
            channelMap.put(channelKey, channelSessionMap);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void closeChannelSession(String channelKey)
    {
        Map<ChannelExec, Session> channelSessionMap = channelMap.get(channelKey);
        if (channelSessionMap != null)
        {
            for (Map.Entry<ChannelExec, Session> map : channelSessionMap.entrySet())
            {
                map.getKey().disconnect();
                map.getValue().disconnect();
            }
            channelMap.remove(channelKey);
        }
    }

}
