package com.choose.Message.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choose.Message.bean.User;
import com.choose.Message.mapper.AccountMapper;
import com.choose.Message.service.UserService;

@Service
public class UserServceImpl implements UserService {

	@Resource
	private AccountMapper accountMapper;
	@Override
	public User userLogin(Map<String, Object> map) {
		return accountMapper.userLogin(map);
	}

}
