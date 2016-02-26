package com.choose.Message.service;

import java.util.Map;

import com.choose.Message.bean.User;

/**
 *用户相关操作服务层
 *@author 吴耀宗
 *@data 2016/2/23
 */
public interface UserService {
	/**
	 *用户登录
	 *@return Account 账号
	 */
	public User userLogin(Map<String,Object> map);
	
}
