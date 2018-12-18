/*
 * Copyright (c) 2018 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * User.
 * @author <A HREF="mailto:zming@extremevalue.cn">Ming.Zhu</A>
 * @version 1.0, $Revision: 0$, $Date: 2018年11月28日$
 * @since 1.0
 */
public class User implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = 1091608354117696371L;

    private String id;

    private String userName;

    private String passWord;

    private String privatePassWord;

    private String name;

    private Boolean gender;
    
    private Date birthday;

    private String phoneNumber;

    private Date createTime;

    private Date updateTime;

    public User()
    {
        super();
    }

    public User(String name, String userName, String passWord, String privatePassWord, Boolean gender, Date birthday, String phoneNumber)
    {
        super();
        this.name = name;
        this.userName = userName;
        this.passWord = passWord;
        this.privatePassWord = privatePassWord;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        Date now = new Date();
        this.createTime = now;
        this.updateTime = now;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getPrivatePassWord()
    {
        return privatePassWord;
    }

    public void setPrivatePassWord(String privatePassWord)
    {
        this.privatePassWord = privatePassWord;
    }

    public Boolean getGender()
    {
        return gender;
    }

    public void setGender(Boolean gender)
    {
        this.gender = gender;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

}
