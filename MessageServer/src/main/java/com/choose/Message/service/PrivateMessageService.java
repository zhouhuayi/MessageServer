package com.choose.Message.service;

import java.util.List;

import org.json.JSONObject;

/**
 * 私聊信息逻辑处理接口
 * 
 * @author 周化益
 *
 */
public interface PrivateMessageService {
	/**
	 * 发送私聊消息
	 * 
	 * @author 周化益
	 * @param jsonObject 私聊的json数据
	 * @return
	 */
	public JSONObject sendMessage(JSONObject jsonObject);
	
	/**
	 * 获取历史记录
	 * 
	 * @author 周化益
	 * @param userId 用户ID
	 * @param userType 用户类型
	 * @param receiveId 接受者ID
	 * @param receiveType 接受者类型
	 * @return
	 */
	public List<JSONObject> getHistory(long userId, int userType, long receiveId, int receiveType);
}