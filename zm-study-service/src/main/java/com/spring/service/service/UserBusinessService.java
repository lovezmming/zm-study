package com.spring.service.service;

import com.spring.service.request.UserLoginRequest;

import java.util.Map;

public interface UserBusinessService
{
    public Map<String, Object> userLogin(UserLoginRequest request);
}
