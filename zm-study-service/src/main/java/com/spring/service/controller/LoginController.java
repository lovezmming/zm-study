package com.spring.service.controller;

import com.spring.service.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/checkAdmin", method = RequestMethod.GET)
    public Boolean checkAdmin(@RequestParam(value = "name") String name)
    {
        return loginService.checkAdmin(name);
    }

}
