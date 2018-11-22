package com.spring.data.controller;

import com.spring.common.application.ApplicationContextFactory;
import com.spring.common.util.JsonUtil;
import com.spring.data.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DataBaseController
{

	@Autowired
	private DataBaseService dataBaseService;

	@PostConstruct
	public void init()
	{
		if (dataBaseService == null)
		{
			dataBaseService = ApplicationContextFactory.getBean(DataBaseService.class);
		}
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@ResponseBody
	public Boolean admin(@RequestParam(value = "name") String name, @RequestParam(value = "type")String type)
	{
		Boolean result = false;
		try
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("name", name);
			paramMap.put("type", type);
			result = dataBaseService.checkAdmin(JsonUtil.toJsonString(paramMap));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
