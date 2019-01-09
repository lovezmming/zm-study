package com.spring.data.controller;

import com.spring.common.application.ApplicationContextFactory;
import com.spring.common.entity.RecordSet;
import com.spring.common.enums.ResultEnum;
import com.spring.data.service.PartnerService;
import com.spring.model.entity.Partner;
import com.spring.model.response.UserBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/partner")
public class PartnerBaseController
{

	@Autowired
	private PartnerService partnerService;

	@PostConstruct
	public void init()
	{
		if (partnerService == null)
		{
			partnerService = ApplicationContextFactory.getBean(PartnerService.class);
		}
	}

	@RequestMapping(value = "/getPartnerByUserId", method = RequestMethod.GET)
	@ResponseBody
	public UserBaseResponse getPartnerByUserId(String userId, String type)
	{
		UserBaseResponse response = new UserBaseResponse();
		try
		{
			Map<String, Object> result = new HashMap<String, Object>();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("type", type);
			RecordSet recordSet = partnerService.getPartnerByUserId(params);
			if (recordSet.getTotalCount() > 0)
			{
				Partner partner = (Partner)recordSet.firstValue();
				result.put("comment", partner.getComment());
				result.put("accessKey", partner.getAccessKey());
				result.put("secretKey", partner.getSecretKey());
			}
			response.setResults(result);
			response.setResultFlag(ResultEnum.success.getValue());
		} catch (Exception e)
		{
			response.setResultDesc(e.getMessage());
			response.setResultFlag(ResultEnum.error.getValue());
		}
		return response;
	}

}
