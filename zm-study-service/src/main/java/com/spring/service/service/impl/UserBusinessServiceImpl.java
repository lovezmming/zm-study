package com.spring.service.service.impl;

import com.spring.model.request.UserBaseRequest;
import com.spring.model.response.UserBaseResponse;
import com.spring.service.mq.kafkaProducer.KafkaSendService;
import com.spring.service.request.RegisterRequest;
import com.spring.service.request.SendMessageRequest;
import com.spring.service.request.UserLoginRequest;
import com.spring.service.service.UserBusinessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    @Autowired
    private KafkaSendService kafkaSendService;

    @Override
    public Map<String, Object> userLogin(UserLoginRequest request)
    {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", request.getUserName());
        params.put("passWord", request.getPassWord());
        params.put("id", null);
        ResponseEntity<UserBaseResponse> responseEntity = restTemplate.getForEntity("http://ZM-STUDY-DATA/user/userLogin?userName={userName}&passWord={passWord}", UserBaseResponse.class, params);
        UserBaseResponse response = responseEntity.getBody();
        return response.getResults();
    }

    @Override public Map<String, Object> register(RegisterRequest request)
    {
        UserBaseRequest userBaseRequest = new UserBaseRequest();
        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("name", request.getName());
        requestMap.put("birthday", request.getBirthday());
        requestMap.put("gender", request.getGender());
        requestMap.put("phoneNumber", request.getPhoneNumber());
        requestMap.put("userName", request.getUserName());
        requestMap.put("passWord", request.getPassWord());
        userBaseRequest.setParams(requestMap);
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<UserBaseRequest> formEntity = new HttpEntity<UserBaseRequest>(userBaseRequest, headers);
        ResponseEntity<UserBaseResponse> userBaseResponse = restTemplate.postForEntity("http://ZM-STUDY-DATA/user/register", formEntity, UserBaseResponse.class);
        UserBaseResponse response = userBaseResponse.getBody();
        return response.getResults();
    }

    @Override
    public Map<String, Object> sendMessage(SendMessageRequest request)
    {
        kafkaSendService.sendMessage(request.getMessage());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", request.getMessage());
        return map;
    }

}