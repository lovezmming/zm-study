package com.spring.data.database.hibernate.dao;

import com.spring.common.entity.RecordSet;

import java.util.Map;

public interface BaseDaoService
{

    public Object find(Class<?> clazz, String id);

    public Object create(Object object);

    public Object update(Object object);

    public void delete(Object object);

    public void delete(Class<?> clazz, String id);

    public RecordSet query(Class<?> clazz, String name, Map<String, Object> params, Integer start, Integer max);
}
