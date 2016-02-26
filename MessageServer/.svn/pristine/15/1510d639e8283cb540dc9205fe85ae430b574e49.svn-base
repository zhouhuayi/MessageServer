package com.choose.Message.service;

import java.util.Map;

/**
 * 用户账号信息业务逻辑操作接口层
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public interface AccountService {
	/**
	 * 用户账号注册方法
	 * 
	 * @author 周化益
	 * @param accountMap 账号信息集合
	 * @return
	 */
	public long addAccount(Map<String, Object> accountMap);
	
	/**
	 * 用户登录方法
	 * 
	 * @author 周化益
	 * @param account 用户账号
	 * @param password	用户密码
	 * @return
	 */
	public Map<String, Object> loginAccount(String account, String password);
	
	/**
	 * 用户修改密码
	 * 
	 * @author 周化益
	 * @param userId 用户ID
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return
	 */
	public boolean updatePwd(long userId, String oldPwd, String newPwd);
	
	/**
	 * 通过ID重置密码找回密码
	 * 
	 * @author 周化益
	 * @param userId 用户ID
	 * @param newPwd 新密码
	 * @return
	 */
	public boolean resetPwdById(long userId, String newPwd);
	
	/**
	 * 验证账号是否存在
	 * 
	 * @author 周化益
	 * @param account 用户账号
	 * @return
	 */
	public boolean validateAccount(String account);
}
