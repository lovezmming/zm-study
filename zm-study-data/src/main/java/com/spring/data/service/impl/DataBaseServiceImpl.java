package com.spring.data.service.impl;

import com.spring.common.util.JsonUtil;
import com.spring.data.database.mybatis.mapper.AdminMapper;
import com.spring.data.database.mybatis.model.ZmSystem;
import com.spring.data.service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseServiceImpl implements DataBaseService
{

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public Boolean checkAdmin(String name)
	{
		Map<String, Object> paramMap = JsonUtil.fromString(name);
		List<ZmSystem> zmSystems = adminMapper.selectZmSystemByNameAndType(paramMap);
		if (zmSystems != null && zmSystems.size() > 0)
		{
			return true;
		}
		return false;
	}

}
