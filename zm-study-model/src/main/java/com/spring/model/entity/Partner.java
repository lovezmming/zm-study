package com.spring.model.entity;

import java.io.Serializable;
import java.util.Date;

public class Partner implements Serializable
{

    /** serialVersionUID */
    private static final long serialVersionUID = -4398834959486253280L;

    public static final String STORAGE = "STORAGE";

    public static final String INIT = "INIT";

    private String id;
    
    private String name;

    private String type;

    private String userId;

    private String accessKey;

    private String secretKey;

    private String comment;

    private Date createTime;

    private Date updateTime;

    public Partner()
    {
        super();
    }

    public Partner(String name, String type, String userId, String accessKey, String secretKey, String comment)
    {
        super();
        this.name = name;
        this.type = type;
        this.userId = userId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.comment = comment;
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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public String getSecretKey()
    {
        return secretKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
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
