package com.spring.common.entity;

import java.io.Serializable;

public class HttpServletBasicResponse implements Serializable
{

    private static final long serialVersionUID = 4433707931553978372L;

    private String resultFlag;

    private String resultDesc;

    public void setResultDesc(String resultDesc)
    {
        this.resultDesc = resultDesc;
    }

    public String getResultDesc()
    {
        return resultDesc;
    }

    public void setResultFlag(String resultFlag)
    {
        this.resultFlag = resultFlag;
    }

    public String getResultFlag()
    {
        return resultFlag;
    }

}
