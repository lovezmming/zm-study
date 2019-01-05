package com.spring.data.controller;

import com.spring.common.application.ApplicationContextFactory;
import com.spring.common.entity.RecordSet;
import com.spring.common.enums.ResultEnum;
import com.spring.common.util.MD5Util;
import com.spring.common.util.TextUtil;
import com.spring.data.service.UserService;
import com.spring.model.entity.User;
import com.spring.model.request.UserBaseRequest;
import com.spring.model.response.UserBaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserBaseController
{

	@Autowired
	private UserService userService;

	@PostConstruct
	public void init()
	{
		if (userService == null)
		{
			userService = ApplicationContextFactory.getBean(UserService.class);
		}
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	@ResponseBody
	public UserBaseResponse userLogin(String userName, String passWord, String id)
	{
		UserBaseResponse response = new UserBaseResponse();
		try
		{
			Map<String, Object> result = new HashMap<String, Object>();
			User user = null;
			if (TextUtil.isEmpty(id))
			{
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("userName", userName);
				params.put("passWord", passWord);
				RecordSet recordSet = userService.getUser(params);
				if (recordSet.getTotalCount() > 0)
				{
					user = (User)recordSet.firstValue();
				}
			} else
			{
				user = userService.getUserById(id);
			}
			if (user != null)
			{
				result.put("name", user.getName());
				result.put("gender", user.getGender());
				result.put("birthday", user.getBirthday());
				result.put("id", user.getId());
				result.put("userName", user.getUserName());
				result.put("passWord", user.getPassWord());
				result.put("phoneNumber", user.getPhoneNumber());
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

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public UserBaseResponse register(UserBaseRequest request)
	{
		UserBaseResponse response = new UserBaseResponse();
		try
		{
			Map<String, Object> params = request.getParams();
			String name = (String)params.get("name");
			Long birthday = (Long)params.get("birthday");
			Boolean gender = (Boolean)params.get("gender");
			String phoneNumber = (String)params.get("phoneNumber");
			String userName = (String)params.get("userName");
			String passWord = (String)params.get("passWord");
			User user = new User(name, userName, passWord, MD5Util.getMD5String(passWord), gender, new Date(birthday), phoneNumber);
			user = userService.createUser(user);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("id", user.getId());
			response.setResults(resultMap);
			response.setResultFlag(ResultEnum.success.getValue());
		} catch (Exception e)
		{
			response.setResultDesc(e.getMessage());
			response.setResultFlag(ResultEnum.error.getValue());
		}
		return response;
	}

}
