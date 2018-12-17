package com.spring.data.service.impl;

import com.spring.common.entity.RecordSet;
import com.spring.common.util.MD5Util;
import com.spring.common.util.ServiceUtil;
import com.spring.data.database.hibernate.dao.BaseDaoService;
import com.spring.data.database.hibernate.entity.User;
import com.spring.data.database.mybatis.mapper.database.UserMapper;
import com.spring.data.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataBaseServiceImpl implements DataBaseService
{

    @Autowired
    private BaseDaoService baseDaoService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getUser(Map<String, Object> params) throws Exception
    {
        String password = (String)params.get("passWord");
        String parivatePassword = MD5Util.getMD5String(password);
        params.put("passWord", parivatePassword);
        RecordSet recordSet = baseDaoService.query(User.class, "getUser", params, null, null);
        Map<String, Object> result = new HashMap<String, Object>();
        if (recordSet.getTotalCount() > 0)
        {
            User user = (User)recordSet.firstValue();
            result.put("name", user.getName());
            result.put("gender", user.getGender());
            result.put("birthday", user.getBirthday());
            result.put("id", user.getId());
            result.put("userName", user.getUserName());
            result.put("passWord", user.getPassWord());
            result.put("phoneNumber", user.getPhoneNumber());
        }
        return result;
    }

    @Override
    public String createUser(Map<String, Object> params) throws Exception
    {
        com.spring.data.database.mybatis.model.User user = new com.spring.data.database.mybatis.model.User();
        Date now = new Date();
        String id = ServiceUtil.getId(null);
        user.setId(id);
        user.setBirthday((Date)params.get("password"));
        user.setUpdateTime(now);
        user.setCreateTime(now);
        user.setGender((Boolean)params.get("gender"));
        user.setName((String)params.get("name"));
        user.setUserName((String)params.get("userName"));
        user.setPassWord((String)params.get("passWord"));
        user.setPhoneNumber((String)params.get("phoneNumber"));
        user.setPrivatePassWord(MD5Util.getMD5String((String)params.get("passWord")));
        userMapper.insert(user);
        return id;
    }

}
