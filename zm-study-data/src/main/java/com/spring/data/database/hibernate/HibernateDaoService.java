package com.spring.data.database.hibernate;

public interface HibernateDaoService
{

    public Object findBySession(Class<?> clazz, String id);

    public Object findById(Class<?> clazz, String id);

}
