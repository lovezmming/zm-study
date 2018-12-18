package com.spring.model.request;

import com.spring.common.entity.HttpServletBasicRequest;

import java.util.Map;

public class UserBaseRequest extends HttpServletBasicRequest
{
    private static final long serialVersionUID = -438503523992527113L;

    private Map<String, Object> params;

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

    public Map<String, Object> getParams()
    {
        return params;
    }
}
