package com.spring.service.controller;

import com.spring.service.request.UserLoginRequest;
import com.spring.service.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/user")
public class UserController
{

    @Autowired
    private UserBusinessService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Map<String, Object> userLogin(UserLoginRequest request)
    {
        return userService.userLogin(request);
    }

}
