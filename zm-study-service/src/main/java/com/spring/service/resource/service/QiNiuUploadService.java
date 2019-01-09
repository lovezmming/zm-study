/*
 * Copyright (c) 2016 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.service.resource.service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.spring.common.util.JsonUtil;
import com.spring.model.entity.Partner;
import com.spring.model.response.UserBaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service("service.upload.qiniu")
@Scope("prototype")
public class QiNiuUploadService implements UploadService
{
    private static final Logger logger = LoggerFactory.getLogger(QiNiuUploadService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String uploadResource(String userId, byte[] data, String foler, String fileName) throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("type", Partner.STORAGE);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/partner/getPartnerByUserId?userId={userId}&type={type}", UserBaseResponse.class, params);
        UserBaseResponse responseBody = responseEntity.getBody();
        Map<String, Object> resultMap = responseBody.getResults();
        Map<String, Object> comment = JsonUtil.fromString((String) resultMap.get("comment"));
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create((String) resultMap.get("accessKey"), (String) resultMap.get("secretKey"));
        String key = foler.endsWith("/") ? foler.concat(fileName) : foler.concat("/").concat(fileName);
        try
        {
            Response response = new UploadManager(cfg).put(data, key, auth.uploadToken(String.valueOf(comment.get("bucketName"))));
            logger.info(response.bodyString());
        } catch (QiniuException e)
        {
            Response response = e.response;
            logger.error(response.toString());
            logger.error(response.bodyString());
            logger.error(e.getMessage(), e);
        }
        return String.valueOf(comment.get("domainName")) + "/" + key;
    }

    @Override
    public String downLoadResource(String userId, String foler, String fileName) throws Exception
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("type", Partner.STORAGE);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/partner/getPartnerByUserId?userId={userId}&type={type}", UserBaseResponse.class, params);
        UserBaseResponse responseBody = responseEntity.getBody();
        Map<String, Object> resultMap = responseBody.getResults();
        Map<String, Object> comment = JsonUtil.fromString((String) resultMap.get("comment"));
        Auth auth = Auth.create((String) resultMap.get("accessKey"), (String) resultMap.get("secretKey"));
        String key = foler.endsWith("/") ? foler.concat(fileName) : foler.concat("/").concat(fileName);
        String url = comment.get("domainName") + "/" + key;
        Boolean isFileExists = false;
        Configuration cfg = new Configuration(Zone.zone0());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try
        {
            bucketManager.stat(String.valueOf(comment.get("bucketName")), key);
            isFileExists = true;
        } catch (QiniuException e)
        {
            Response response = e.response;
            logger.error(response.toString());
            logger.error(response.bodyString());
            logger.error(e.getMessage(), e);
        }
        return url = isFileExists == true ? url : null;
    }

}
