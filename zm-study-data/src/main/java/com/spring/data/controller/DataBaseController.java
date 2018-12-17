package com.spring.data.controller;

import com.spring.common.application.ApplicationContextFactory;
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

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> userLogin(@RequestParam(value = "userName") String userName, @RequestParam(value = "passWord")String passWord)
	{
		Map<String, Object> result = null;
		try
		{
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userName", userName);
			paramMap.put("passWord", passWord);
			result = dataBaseService.getUser(paramMap);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
