package com.choose.Message.service.impl;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.choose.Message.service.PublicMessageService;
import com.choose.Message.util.CommonUtil;
import com.choose.Message.util.DateStrConvert;
import com.choose.Message.util.Redis;

import redis.clients.jedis.Jedis;

/**
 * 私聊信息逻辑处理接口
 * 
 * @author 周化益
 *
 */
@Service
public class PublicMessageServiceImpl implements PublicMessageService {
	@Override
	public JSONObject sendMessage(JSONObject jsonObject) {
		//获取获取redis客户端的jedis
//		Jedis jedis = InitServer.jedis;
		Jedis jedis = Redis.getJedis();
		
		//初始化响应json数据
		JSONObject resultJson = new JSONObject();
		
		//初始化时间
		jsonObject.put("sendTime", DateStrConvert.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//获取发送人ID
		jsonObject.put("sendPerson", 2);
		
		//存储的key值
		StringBuffer messageKey = new StringBuffer();
		messageKey.append(CommonUtil.getDateTime())
		.append("_publicMessage_")
		.append(jsonObject.get("roomId"));
		
		//将消息添加到redis缓存中，并获取响应结果
		long state = jedis.rpush(messageKey.toString(),jsonObject.toString());
		
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
}
