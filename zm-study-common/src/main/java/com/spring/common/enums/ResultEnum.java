/*
 * Copyright (c) 2017 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.enums;

/**
 * CourseArrangementResultEnum.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年11月29日$
 * @since 1.0
 */
public enum ResultEnum
{
    success("成功", "1"),

    fail("排失败", "0"),

    error("异常", "-1");

    private String _name;
    private String _value;

    private ResultEnum(String name, String value)
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
