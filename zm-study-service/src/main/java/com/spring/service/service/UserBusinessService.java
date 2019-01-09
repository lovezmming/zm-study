package com.spring.service.service;

import com.spring.service.request.RegisterRequest;
import com.spring.service.request.SendMessageRequest;
import com.spring.service.request.UserLoginRequest;

import java.util.Map;

public interface UserBusinessService
{
    public Map<String, Object> userLogin(UserLoginRequest request);

    public Map<String, Object> register(RegisterRequest request);

    public Map<String, Object> sendMessage(SendMessageRequest request);
}
