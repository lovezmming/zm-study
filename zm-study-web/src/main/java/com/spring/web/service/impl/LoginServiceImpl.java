package com.spring.web.service.impl;

import com.spring.web.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService
{
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean checkAdmin(String name)
    {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<Boolean> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/admin?name={name}&type=ADMIN", Boolean.class, map);
        Boolean callResult = responseEntity.getBody();
        logger.info("访问checkAdmin结果：" + callResult);
        return callResult;
    }

}
