package com.spring.common.enums;

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
