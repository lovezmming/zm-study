package com.spring.common.enums;

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
