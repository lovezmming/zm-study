package com.spring.data.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface DataBaseService
{
	public Boolean checkAdmin(@RequestParam(value = "name") String name);

}
