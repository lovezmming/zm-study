package com.spring.service.service.impl;

import com.spring.common.enums.ResultEnum;
import com.spring.model.request.UserBaseRequest;
import com.spring.model.response.UserBaseResponse;
import com.spring.service.request.UserLoginRequest;
import com.spring.service.service.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserBusinessServiceImpl implements UserBusinessService
{
    private static final Logger logger = LoggerFactory.getLogger(UserBusinessServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public Map<String, Object> userLogin(UserLoginRequest request)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", request.getUserName());
        params.put("passWord", request.getPassWord());
        UserBaseRequest userBaseRequest = new UserBaseRequest();
        userBaseRequest.setParams(params);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/userLogin", UserBaseResponse.class, request);
        UserBaseResponse response = responseEntity.getBody();
        return response.getResults();
    }

}