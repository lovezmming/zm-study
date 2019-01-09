package com.spring.service.elasticsearch.service;

import com.spring.common.entity.RecordSet;

import java.util.Map;

public interface SearchService
{
    public void initSearchInfo(String userId, String index, String type, Map<String, Object> params) throws Exception;

    public RecordSet searchUserInfo(String userId, String index, String type, Map<String, Object> params) throws Exception;
}
