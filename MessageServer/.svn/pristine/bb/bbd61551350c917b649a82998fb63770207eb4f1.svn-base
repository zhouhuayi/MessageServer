package com.choose.Message.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choose.Message.mapper.AccountMapper;
import com.choose.Message.mapper.UserMapper;
import com.choose.Message.service.AccountService;
import com.choose.Message.util.CommonUtil;

/**
 * 用户账号信息业务逻辑操作实现层
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
@Service
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountMapper accountDao;
	
	@Resource
	private UserMapper userDao;
	
	@Override
	public long addAccount(Map<String, Object> map) {
		Map<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("account_name", map.remove("account_name"));
		accountMap.put("account_password", map.remove("account_password"));
		
		long userId = userDao.registerUser(map);
		
		if(userId > 0) {
			accountMap.put("account_userId", userId);
			accountMap.put("account_type", 1);
			accountMap.put("account_source", 1);
			return accountDao.addAccount(accountMap);
		} else {
			return userId;
		}
	}

	@Override
	public Map<String, Object> loginAccount(String account, String password) {
		return accountDao.loginAccount(account, CommonUtil.string2MD5(password));
	}

	@Override
	public boolean updatePwd(long userId, String oldPwd, String newPwd) {
		return accountDao.updatePwd(userId, oldPwd, newPwd);
	}

	@Override
	public boolean resetPwdById(long userId, String newPwd) {
		return accountDao.resetPwdById(userId, newPwd);
	}

	@Override
	public boolean validateAccount(String account) {
		return accountDao.validateAccount(account);
	}
}
