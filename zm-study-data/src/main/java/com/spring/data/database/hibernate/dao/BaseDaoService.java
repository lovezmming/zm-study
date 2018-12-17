/*
 * Copyright (c) 2018 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.data.database.hibernate.dao;

import com.spring.common.entity.RecordSet;

import java.util.Map;

/**
 * BaseDaoService.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年11月30日$
 * @since 1.0
 */
public interface BaseDaoService
{

    /**
     * find
     * @param clazz
     * @param id
     * @return
     */
    public Object find(Class<?> clazz, String id);

    /**
     * create
     * @param object
     * @return
     */
    public Object create(Object object);

    /**
     * update
     * @param object
     * @return
     */
    public Object update(Object object);

    /**
     * delete
     * @param object
     */
    public void delete(Object object);

    /**
     * delete
     * @param clazz
     * @param id
     */
    public void delete(Class<?> clazz, String id);

    /**
     * query
     * @param clazz
     * @param name
     * @param params
     * @param start
     * @param max
     * @return
     */
    public RecordSet query(Class<?> clazz, String name, Map<String, Object> params, Integer start, Integer max);
}
