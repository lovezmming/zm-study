package com.spring.data.service;

import com.spring.common.entity.RecordSet;

import java.util.Map;

public interface PartnerService
{
    public RecordSet getPartnerByUserId(Map<String, Object> params) throws Exception;

}
