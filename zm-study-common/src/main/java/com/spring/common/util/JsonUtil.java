package com.spring.common.util;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonUtil
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJsonString(Map<String, Object> jsonObject)
    {
        try
        {
            return objectMapper.writeValueAsString(jsonObject);    
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object[] jsonObject)
    {
        try
        {
            return objectMapper.writeValueAsString(jsonObject);    
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromString(String jsonContent)
    {
        try
        {
            return objectMapper.readValue(jsonContent, Map.class);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromListJson(String str,Class<T> clazz){  
        return JSONArray.parseArray(str, clazz);  
    }  

}
