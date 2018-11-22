package com.spring.data.database.mybatis.mapper;

import com.spring.data.database.mybatis.model.ZmSystem;

import java.util.List;
import java.util.Map;

public interface AdminMapper
{

    List<ZmSystem> selectZmSystemByNameAndType(Map<String, Object> map);

}