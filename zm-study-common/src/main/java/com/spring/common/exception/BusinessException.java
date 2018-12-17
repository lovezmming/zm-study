package com.spring.common.exception;

public class BusinessException extends Exception
{

    /** serialVersionUID */
    private static final long serialVersionUID = 4900768045942035709L;

    private String errorCode;

    private String errorMessage;

    public BusinessException()
    {
    }

    public BusinessException(String errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
}
