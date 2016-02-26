package com.choose.test;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choose.Message.mapper.PrivateMessageMapper;
import com.choose.Message.mapper.PublicMessageMapper;
import com.choose.Message.service.PrivateMessageService;
import com.choose.Message.service.PublicMessageService;
import com.choose.Message.util.CommonUtil;
import com.choose.Message.util.Redis;
import com.choose.Message.util.SpringTask;

import redis.clients.jedis.Jedis;

/**
 * 测试数据库的交互操作
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:com/choose/Message/resource/spring-mybatis.xml")
public class testDao {
	@Resource
	private PrivateMessageMapper privateMessageMapper;
	@Resource
	private PublicMessageMapper publicMessageMapper;
	@Resource
	private PrivateMessageService privateMessageService;
	@Resource
	private PublicMessageService PublicMessageService;
	@Resource
	private SpringTask springTask;
	
	@Test
	public void test() {
		Jedis jedis = Redis.getJedis();
//		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
//		
//		for (int i = 0; i < 10; i++) {
//			Map<String, Object> addMap = new HashMap<String, Object>();
//			addMap.put("sendPerson", 1);
//			addMap.put("receivePerson", 1);
//			addMap.put("sendTime", DateStrConvert.dateToStr(new Date(), "yyyy-MM-dd"));
//			addMap.put("content", "测试内容"+i);
//			addMap.put("roomId", 1000+i);
//			addMap.put("state", 1);
//			addMap.put("sendType", 1);
////			addMap.put("receiveType", 1);
//			addMap.put("receiveType", 1);
//			listMap.add(addMap);
//		}
//		
//		MessagePojo message = new MessagePojo();
//		message.setAddListMap(listMap);
//		
//		System.out.println(publicMessageMapper.bathAdd(message));
//		jedis.del("priavteMessage");
//		JSONObject addMap = new JSONObject();
//		addMap.put("sendPerson", 1);
//		addMap.put("receivePerson", 1);
////		addMap.put("sendTime", DateStrConvert.dateToStr(new Date(), "yyyy-MM-dd"));
//		addMap.put("content", "测试内容");
//		addMap.put("roomId", 1000);
//		addMap.put("state", 1);
//		addMap.put("sendType", 1);
//		addMap.put("receiveType", 1);
////		System.out.println(privateMessageService.sendMessage(addMap));
//		
//		String searchKey = CommonUtil.getDateTime() + "_privateMessage*";
//		System.out.println(searchKey);
//		
//		System.out.println(jedis.keys(searchKey));
//		System.out.println(jedis.keys("*privateMessage*"));
		
//		jedis.flushDB();
//		
//		JSONObject addMap = new JSONObject();
//		addMap.put("sendPerson", 1);
////		addMap.put("sendTime", DateStrConvert.dateToStr(new Date(), "yyyy-MM-dd"));
//		addMap.put("content", "测试内容");
//		addMap.put("roomId", 1000);
//		addMap.put("state", 1);
//		addMap.put("sendType", 1);
//		System.out.println(PublicMessageService.sendMessage(addMap));
//		System.out.println(jedis.keys("*publicMessage*"));
//		springTask.doMessage();
		System.out.println(jedis.keys("*_privateMessage_1_1_1_1_false"));
		System.out.println(privateMessageService.getHistory(1, 1, 1, 1));
	}
}
