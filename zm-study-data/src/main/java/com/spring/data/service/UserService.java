package com.spring.data.service;

import com.spring.common.entity.RecordSet;
import com.spring.model.entity.User;

import java.util.Map;

public interface UserService
{
    public RecordSet getUser(Map<String, Object> params) throws Exception;

    public User createUser(User user) throws Exception;

    public User getUserById(String id) throws Exception;
}
