package com.spring.data.service;

import java.util.Map;

public interface DataBaseService
{
    public Map<String, Object> getUser(Map<String, Object> params) throws Exception;

    public String createUser(Map<String, Object> params) throws Exception;
}
