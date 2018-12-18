package com.spring.model.response;

import com.spring.common.entity.HttpServletBasicResponse;

import java.util.Map;

public class UserBaseResponse extends HttpServletBasicResponse
{
    private static final long serialVersionUID = 2999662947222166197L;

    private Map<String, Object> results;

    public Map<String, Object> getResults()
    {
        return results;
    }

    public void setResults(Map<String, Object> results)
    {
        this.results = results;
    }
}
