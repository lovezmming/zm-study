package com.spring.data.service.impl;

import com.spring.common.entity.RecordSet;
import com.spring.common.util.MD5Util;
import com.spring.common.util.ServiceUtil;
import com.spring.data.database.hibernate.dao.BaseDaoService;
import com.spring.data.service.UserService;
import com.spring.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private BaseDaoService baseDaoService;

    @Override
    public RecordSet getUser(Map<String, Object> params) throws Exception
    {
        String password = (String)params.get("passWord");
        String parivatePassword = MD5Util.getMD5String(password);
        params.put("passWord", parivatePassword);
        return baseDaoService.query(User.class, "getUser", params, null, null);
    }

    @Override
    public User createUser(User user) throws Exception
    {
        String id = ServiceUtil.getId(null);
        user.setId(id);
        user = (User)baseDaoService.create(user);
        return user;
    }

    @Override
    public User getUserById(String id) throws Exception
    {
        return (User)baseDaoService.find(User.class, id);
    }

}
