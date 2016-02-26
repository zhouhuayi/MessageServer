package com.choose.Message.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.choose.Message.WebSocket.InitServer;
import com.choose.Message.mapper.PrivateMessageMapper;
import com.choose.Message.mapper.PublicMessageMapper;
import com.choose.Message.pojo.MessagePojo;

import redis.clients.jedis.Jedis;

/**
 * spring 的定时任务类
 * 
 * @author 周化益
 *
 */
@Service
public class SpringTask {
	
	/**
	 * 定时消息与数据库进行同步
	 */
	public void doMessage() {
		System.out.println("进入spring定时任务");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				addPrivateMessage();
			}
		});
		
		thread.start();
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				addPublicMessage();
			}
		});
		
		thread1.start();
	}
	
	/**
	 * 将私聊消息同步到数据库中
	 * 
	 * @author 周化益
	 */
	public static void addPrivateMessage() {
		//初始化私聊Mapper
		PrivateMessageMapper privateMessageMapper = InitServer.context.getBean(PrivateMessageMapper.class);
		
		//初始化Redis客户端
		Jedis jedis = InitServer.jedis;
		
		//查询Redis当天私聊消息存储的KEY值
		Set<String> messageKey = jedis.keys(CommonUtil.getDateTime() + "_privateMessage*");
		
		//初始化List，用来接收当日所有的私聊数据
		List<Map<String, Object>> messageList = new ArrayList<Map<String, Object>>();
		
		/*循环今日私聊的所有KEY值*/
		for (String key : messageKey) {
			//从Redis读取KEY对应的所有数据
			List<String> redisMessageList = jedis.lrange(key, 0, -1);
			
			/*循环KEY值对应的所有数据*/
			for (String jsonStr : redisMessageList) {
				//定义接受对象
				Map<String, Object> messageMap = new HashMap<String, Object>();
				
				//将取出来的数据转换成JSON存储
				JSONObject jsonData = new JSONObject(jsonStr);
				
				//将Json转换成Map数据
				messageMap = BeanConvertMap.jsonToMap(jsonData);
				
				//将map存入List集合中
				messageList.add(messageMap);
			}
		}
		
		//初始换消息映射类
		MessagePojo messagePojo = new MessagePojo();
		messagePojo.setAddListMap(messageList);
		
		//调用消息批量添加访法
		privateMessageMapper.bathAdd(messagePojo);
	}
	
	/**
	 * 将公聊消息同步到数据库中
	 * 
	 * @author 周化益
	 */
	public static void addPublicMessage() {
		//初始化公聊Mapper
		PublicMessageMapper publicMessageMapper = InitServer.context.getBean(PublicMessageMapper.class);
		
		//初始化Redis客户端
		Jedis jedis = InitServer.jedis;
		
		//查询Redis当天公聊消息存储的KEY值
		Set<String> messageKey = jedis.keys(CommonUtil.getDateTime() + "_publicMessage*");
		
		//初始化List，用来接收当日所有的私聊数据
		List<Map<String, Object>> messageList = new ArrayList<Map<String, Object>>();
		
		/*循环KEY值对应的所有数据*/
		for (String key : messageKey) {
			//从Redis读取KEY对应的所有数据
			List<String> redisMessageList = jedis.lrange(key, 0, -1);
			
			/*循环KEY值对应的所有数据*/
			for (String jsonStr : redisMessageList) {
				//定义接受对象
				Map<String, Object> messageMap = new HashMap<String, Object>();
				
				//将取出来的数据转换成JSON存储
				JSONObject jsonData = new JSONObject(jsonStr);
				
				//将Json转换成Map数据
				messageMap = BeanConvertMap.jsonToMap(jsonData);
				
				//将map存入List集合中
				messageList.add(messageMap);
			}
		}
		
		//初始换消息映射类
		MessagePojo messagePojo = new MessagePojo();
		messagePojo.setAddListMap(messageList);
		
		//调用消息批量添加访法
		publicMessageMapper.bathAdd(messagePojo);
	}
}
