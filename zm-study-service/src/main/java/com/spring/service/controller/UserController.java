package com.spring.service.controller;

import com.spring.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController("/user")
public class UserController
{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.GET)
    public Map<String, Object> userLogin(@RequestParam(value = "name") String name)
    {
        return userService.userLogin(name);
    }

}
