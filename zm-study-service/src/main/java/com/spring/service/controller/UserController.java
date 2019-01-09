package com.spring.service.controller;

import com.spring.service.request.RegisterRequest;
import com.spring.service.request.SendMessageRequest;
import com.spring.service.request.UserLoginRequest;
import com.spring.service.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{

    @Autowired
    private UserBusinessService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Map<String, Object> userLogin(UserLoginRequest request)
    {
        return userService.userLogin(request);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, Object> register(RegisterRequest request)
    {
        return userService.register(request);
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public Map<String, Object> sendMessage(SendMessageRequest request)
    {
        return userService.sendMessage(request);
    }
}
