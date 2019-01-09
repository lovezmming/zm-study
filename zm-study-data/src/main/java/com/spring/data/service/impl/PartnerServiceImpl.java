package com.spring.data.service.impl;

import com.spring.common.entity.RecordSet;
import com.spring.data.database.hibernate.dao.BaseDaoService;
import com.spring.data.service.PartnerService;
import com.spring.model.entity.Partner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PartnerServiceImpl implements PartnerService
{

    @Autowired
    private BaseDaoService baseDaoService;

    @Override
    public RecordSet getPartnerByUserId(Map<String, Object> params) throws Exception
    {
        return baseDaoService.query(Partner.class, "getPartnerByUserId", params, null, null);
    }
}
