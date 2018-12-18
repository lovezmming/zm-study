package com.spring.service.request;

import com.spring.common.entity.HttpServletBasicRequest;

import javax.validation.constraints.NotNull;

public class UserLoginRequest extends HttpServletBasicRequest
{

    /** serialVersionUID */
    private static final long serialVersionUID = 4809730480074878758L;

    @NotNull
    private String userName;

    @NotNull
    private String passWord;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassWord()
    {
        return passWord;
    }

    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }

}
