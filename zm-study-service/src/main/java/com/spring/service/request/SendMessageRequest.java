package com.spring.service.request;

import com.spring.common.entity.HttpServletBasicRequest;

import javax.validation.constraints.NotNull;

public class SendMessageRequest extends HttpServletBasicRequest
{

    /** serialVersionUID */
    private static final long serialVersionUID = 4809730480074878758L;

    @NotNull
    private String message;

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }
}
