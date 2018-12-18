package com.spring.service.request;

import com.spring.common.entity.HttpServletBasicRequest;

import javax.validation.constraints.NotNull;

public class RegisterRequest extends HttpServletBasicRequest
{

    /** serialVersionUID */
    private static final long serialVersionUID = -7565844895784343344L;

    private String name;

    private Long birthday;

    private Boolean gender;

    private String phoneNumber;

    @NotNull
    private String userName;

    @NotNull
    private String passWord;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Long getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Long birthday)
    {
        this.birthday = birthday;
    }

    public Boolean getGender()
    {
        return gender;
    }

    public void setGender(Boolean gender)
    {
        this.gender = gender;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

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
