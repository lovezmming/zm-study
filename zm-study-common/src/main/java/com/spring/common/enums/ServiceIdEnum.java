/*
 * Copyright (c) 2017 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.enums;


/**
 * ServiceIdEnum.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年11月29日$
 * @since 1.0
 */
public enum ServiceIdEnum
{
    zm_study("01", "01");

    private String _name;
    private String _value;

    private ServiceIdEnum(String name, String value)
    {
        _name = name;
        _value = value;
    }

    public String getName()
    {
        return _name;
    }

    public String getValue()
    {
        return _value;
    }

}
