package com.spring.service.resource.service;

public interface UploadService
{

    public String uploadResource(String tenantId, byte[] content, String foler, String fileName) throws Exception;

    public String downLoadResource(String userId, String foler, String fileName) throws Exception;

}
