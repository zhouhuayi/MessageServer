package com.choose.Message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.choose.Message.service.PrivateMessageService;
import com.choose.Message.util.CommonUtil;
import com.choose.Message.util.DateStrConvert;
import com.choose.Message.util.Redis;

import redis.clients.jedis.Jedis;

/**
 * 私聊逻辑处理类
 * 
 * @author 周化益
 *
 */
@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {
	
	@Override
	public JSONObject sendMessage(JSONObject jsonObject) {
		//获取获取redis客户端的jedis
//		Jedis jedis = InitServer.jedis;
		Jedis jedis = Redis.getJedis();
		
		//初始化响应json数据
		JSONObject resultJson = new JSONObject();
		
		//初始化时间
		jsonObject.put("sendTime", DateStrConvert.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//获取发送人Id
		jsonObject.put("sendPerson", 11);
		//获取反送人类型
		jsonObject.put("sendType", 1);
		
		//获取是否在线
		boolean bool = jedis.exists("user_" + jsonObject.get("sendPerson") + "_" + jsonObject.get("sendType"));
		
		//存储的Key值
		StringBuffer messageKey = new StringBuffer();
		messageKey.append(CommonUtil.getDateTime())
		.append("_privateMessage_")
		.append(jsonObject.get("sendPerson")).append('_')
		.append(jsonObject.get("sendType")).append('_')
		.append(jsonObject.get("receivePerson")).append('_')
		.append(jsonObject.get("receiveType")).append('_')
		.append(bool);
		
		//将消息添加到redis缓存中，并获取响应结果
		long state = jedis.rpush(messageKey.toString(), jsonObject.toString());
		
		/*根据redis的响应结果进行相应的判断与赋值*/
		if(state > 0) {
			resultJson.put("type", "success");
			resultJson.put("message", "发送成功");
		} else {
			resultJson.put("type", "fail");
			resultJson.put("message", "发送失败");
		}
		
		return resultJson;
	}
	
	@Override
	public List<JSONObject> getHistory(long userId, int userType, long receiveId, int receiveType) {
		//获取获取redis客户端的jedis
//		Jedis jedis = InitServer.jedis;
		Jedis jedis = Redis.getJedis();
		
		//存储的Key值
		String privateKey = "*_privateMessage_" + userId + "_" + userType + "_" + receiveId + "_" + receiveType + "_false";
		
		//查询Redis当天私聊消息存储的KEY值
		Set<String> messageKey = jedis.keys(privateKey);
		
		//初始化List，用来接收当日所有的私聊数据
		List<JSONObject> messageList = new ArrayList<JSONObject>();
		
		/*循环今日私聊的所有KEY值*/
		for (String key : messageKey) {
			//从Redis读取KEY对应的所有数据
			List<String> redisMessageList = jedis.lrange(key, 0, -1);
			
			/*循环KEY值对应的所有数据*/
			for (String jsonStr : redisMessageList) {
				//将取出来的数据转换成JSON存储
				JSONObject jsonData = new JSONObject(jsonStr);
				
				//将map存入List集合中
				messageList.add(jsonData);
			}
		}
		return messageList;
	}
}
