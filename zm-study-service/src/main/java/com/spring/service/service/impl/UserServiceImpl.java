package com.spring.service.service.impl;

import com.spring.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Map<String, Object> userLogin(String name)
    {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<Map> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/userLogin?userName={userName}&passWord={passWord}", Map.class, map);
        Map<String, Object> callResult = responseEntity.getBody();
        return callResult;
    }

}
