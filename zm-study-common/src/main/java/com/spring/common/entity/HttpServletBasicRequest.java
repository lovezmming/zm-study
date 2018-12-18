/*
 * Copyright (c) 2016 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.entity;

import java.io.Serializable;

public class HttpServletBasicRequest implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 7296555444241002139L;

    private Integer pageNumber;

    private Integer pageSize;

    public Integer getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getStart()
    {
        if (getPageNumber() != null && getPageSize() != null)
        {
            return getPageNumber() * getPageSize();
        } else
        {
            return null;
        }
    }

    public Integer getMax()
    {
        return getPageSize();
    }

}
