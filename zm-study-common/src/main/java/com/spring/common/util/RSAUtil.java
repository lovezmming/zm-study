package com.spring.common.util;

import javax.crypto.Cipher;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RSAUtil
{
    public static final String INTEGER_BASE64_MAPPING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    
    private static HashMap<String, RSAPublicKey> publicKeyCache = new HashMap<String, RSAPublicKey>();
    
    private static HashMap<String, RSAPrivateKey> privateKeyCache = new HashMap<String, RSAPrivateKey>();
    
    public static RSAPublicKey getPublicKey(BigInteger modulus, BigInteger publicExponent) throws Exception
    {
        String key = modulus + "_" + publicExponent;
        RSAPublicKey publicKey = publicKeyCache.get(key);
        if (publicKey == null)
        {
            synchronized (publicKeyCache)
            {
                publicKey = publicKeyCache.get(key);
                if (publicKey == null)
                {
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, publicExponent);
                    publicKey = (RSAPublicKey) factory.generatePublic(keySpec);
                    publicKeyCache.put(key, publicKey);
                }
            }
        }

        return publicKey;
    }
    
    public static RSAPrivateKey getPrivateKey(BigInteger modulus, BigInteger privateExponent) throws Exception
    {
        String key = modulus + "_" + privateExponent;
        RSAPrivateKey privateKey = privateKeyCache.get(key);
        if (privateKey == null)
        {
            synchronized (privateKeyCache)
            {
                privateKey = privateKeyCache.get(key);
                if (privateKey == null)
                {
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, privateExponent);
                    privateKey = (RSAPrivateKey) factory.generatePrivate(keySpec);
                    privateKeyCache.put(key, privateKey);
                }
            }
        }

        return privateKey;
    }
    
    public static String encryptByPublicKey(String value, BigInteger modulus, BigInteger publicExponent) throws Exception
    {
        return encryptByPublicKey(value, getPublicKey(modulus, publicExponent));
    }
    
    public static String encryptByPublicKey(String value, RSAPublicKey publicKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int keyLen = publicKey.getModulus().bitLength() / 8;
        List<String> datas = TextUtil.split(value, keyLen - 11);
        StringBuffer buffer = new StringBuffer();
        for (String data : datas)
            buffer.append(TextUtil.bcd2Str(cipher.doFinal(data.getBytes())));
        return buffer.toString();
    }
    
    public static String decryptByPrivateKey(String value, BigInteger modulus, BigInteger privateExponent) throws Exception
    {
        return decryptByPrivateKey(value, getPrivateKey(modulus, privateExponent));
    }
    
    public static String decryptByPrivateKey(String value, RSAPrivateKey privateKey) throws Exception
    {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int keyLen = privateKey.getModulus().bitLength() / 8;
        byte[] bytes = value.getBytes();
        byte[] bcd = TextUtil.ascii2bcd(bytes, bytes.length);
        Collection<byte[]> arrays = TextUtil.split(bcd, keyLen);
        StringBuffer buffer = new StringBuffer();
        for (byte[] arr : arrays)
            buffer.append(new String(cipher.doFinal(arr)));
        return buffer.toString();
    }
    
    public static String encode4Integer(int number)
    {
        return padding4Binary(Integer.toBinaryString(number));
    }
    
    public static String encode4BigInteger(BigInteger number)
    {
        return padding4Binary(number.toString(2));
    }

    public static String encode4BigInteger(String number)
    {
        return padding4Binary(new BigInteger(number).toString(2));
    }
    
    private static String padding4Binary(String binary)
    {
        int mod = binary.length() % (3 * 8);
        if (mod == 0)
            return binary;
        
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < (3 * 8 - mod); i++)
            buffer.append("0");
        buffer.append(binary);
        
        String delimiter = "00";
        StringBuffer paddingDoubleZero = new StringBuffer();
        int size = buffer.length() / 6;
        for (int i = 0; i < size; i++)
        {
            CharSequence cs = buffer.subSequence(i * 6, (i + 1) * 6);
            if (cs.length() > 0)
                paddingDoubleZero.append(delimiter).append(cs);
        }
        
        StringBuffer result = new StringBuffer();
        BigInteger index = null;
        size = paddingDoubleZero.length() / 8;
        String tmp = null;
        for (int i = 0; i < size; i++)
        {
            tmp = paddingDoubleZero.substring(i * 8, (i + 1) * 8);
            if (!TextUtil.isEmpty(tmp))
            {
                index = new BigInteger(tmp, 2);
                result.append(INTEGER_BASE64_MAPPING.charAt(index.intValue()));
            }
        }
        return result.toString();
    }
    
    public static  String encode4PublicKey(BigInteger modulus, BigInteger publicExponent) throws Exception
    {
        RSAPublicKey publicKey = RSAUtil.getPublicKey(modulus, publicExponent);
        return Base64.encode(publicKey.getEncoded());
    }
    
    
    
    public static void main(String[] args)
    {
        /*int number = 65537;
        BigInteger bi = new BigInteger("113548689003243511938095607654140722957756900159626261293920067435343607554293937682785664472707437275107690627334437209076204287056574662974807780290279438252468090234476342526516491040361370015336446952325203000603776684576893044377797687285775816228283719654773377325415225569093670923647401106209541125699");
        try
        {
            String binaryNumber = Integer.toBinaryString(number); 
            String paddingNumber = padding4IOS(binaryNumber);
            System.out.println(paddingNumber);
            
            String bigBinaryNumber = bi.toString(2);
            System.out.println(bigBinaryNumber);
            System.out.println(padding4IOS(bigBinaryNumber));
        } catch (Exception e)
        {
        }*/
        
        try
        {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);  
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            
            System.out.println("encoded public key:" + Base64.encode(publicKey.getEncoded()));
            
            System.out.println(publicKey.getModulus() + "#" + publicKey.getPublicExponent());
            System.out.println(privateKey.getModulus() + "#" + privateKey.getPrivateExponent());
            
            publicKey = getPublicKey(publicKey.getModulus(), publicKey.getPublicExponent());
            privateKey = getPrivateKey(privateKey.getModulus(), privateKey.getPrivateExponent());
            
            UUID uuid = UUID.randomUUID();
            System.out.println("uuid1:" + uuid.toString());
            uuid = UUID.randomUUID();
            System.out.println("uuid2:" + uuid.toString());
            
            long start = System.currentTimeMillis();
            String secretText = encryptByPublicKey("a", publicKey);
            System.out.println(secretText);
            System.out.println(System.currentTimeMillis() - start);
            String result = decryptByPrivateKey(secretText, privateKey);
            System.out.println(result);
            System.out.println(System.currentTimeMillis() - start);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}