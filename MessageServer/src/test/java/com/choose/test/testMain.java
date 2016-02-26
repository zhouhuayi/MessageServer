package com.choose.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.choose.Message.util.Redis;

import redis.clients.jedis.Jedis;

public class testMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("cc", 1);
//		Map<String, Object> map1 =  aa(map);
//		
////		Map map2 = 
//		map.put("cc", 2);
//		System.out.println("map1:"+map1);
//		
//		testInteger v = new testInteger();
//		testInteger b = bb(v);
//		v.num = 2;
//		System.out.println(b.num);
//		
//		
//		
//		
//		System.err.println("=======");
		
//		Jedis jedis = Redis.getJedis();
//		
////		System.out.println(jedis.spop("loginList"));
//		System.out.println(jedis.del("loginList"));
//		
//		JSONObject json = new JSONObject();
//		Map<String, Object> userMap = new HashMap<String, Object>();
//		userMap.put("userId", 1);
//		userMap.put("userName", "aaa");
//		Map<String, Object> jsonMap = new HashMap<String, Object>();
//		jsonMap.put("用户ID", "124124");
//		
//		json.put("连接ID", jsonMap);
//		
//		System.out.println(jedis.sadd("loginList", json.toString()));
//		
//		System.out.println(jedis.smembers("loginList"));
//		
//		User user = new User();
//		User user1 = new User();
//		User user2 = new User();
//		System.out.println(user.toString());
//		List<Object> alist = new ArrayList<Object>();
//		alist.add(user);
//		alist.add(user1);
//		alist.add(user2);
//		System.out.println(alist);
		
//		JSONObject jsonObj = new JSONObject();
//		jsonObj.put("json1", 1);
//		jsonObj.put("json2", 2);
//		Map<String, Object> jsonMap1 = jsonObj;
		
//		System.out.println(jsonMap1);
		
//		Map<String, List<Map<String,List<Map<String, Object>>>>> redisMap = new HashMap<String, List<Map<String,List<Map<String, Object>>>>>();
//		
//		Map<String, Object> connMap = new HashMap<String, Object>();
//		JSONObject userJson = new JSONObject();
//		userJson.put("roomId", 10001);
//		userJson.put("account", "admin");
//		userJson.put("password", "123");
//		userJson.put("name", "你不懂");
//		
//		connMap.put("conn1", userJson);
//		
//		Map<String, Object> connMap1 = new HashMap<String, Object>();
//		JSONObject userJson1 = new JSONObject();
//		userJson1.put("roomId", 10002);
//		userJson1.put("account", "scott");
//		userJson1.put("password", "456");
//		userJson1.put("name", "你也不懂");
//		
//		connMap1.put("conn2", userJson);
//		
//		List<Map<String, Object>> connList = new ArrayList<Map<String, Object>>();
//		connList.add(connMap);
//		connList.add(connMap1);
//		
//		Map<String, List<Map<String, Object>>> userMap = new HashMap<String, List<Map<String, Object>>>();
//		userMap.put("user1_2", connList);
//		
//		Map<String, List<Map<String, Object>>> userMap1 = new HashMap<String, List<Map<String, Object>>>();
//		userMap.put("user2_1", connList);
//		
//		Map<String, List<Map<String, Object>>> userMap2 = new HashMap<String, List<Map<String, Object>>>();
//		userMap.put("user3_1", connList);
//		
//		List<Map<String,List<Map<String, Object>>>> roomList = new ArrayList<Map<String,List<Map<String, Object>>>>();
//		roomList.add(userMap);
//		roomList.add(userMap1);
//		roomList.add(userMap2);
//		
//		redisMap.put("room1", roomList);
//		System.out.println(redisMap);
		
		Jedis jedis = Redis.getJedis();
		
		Set<String> userList = new HashSet<String>();
		for (int i = 0; i < 10; i++) {
			if(i%2 == 0) {
				userList.add("userId"+ i + "_type1");
				jedis.sadd("room1", "userId"+ i + "_type1");
				jedis.sadd("room2", "userId"+ i + "_type1");
			} else {
				userList.add("userId"+ i + "_type2");
				jedis.sadd("room1", "userId"+ i + "_type2");
				jedis.sadd("room2", "userId"+ i + "_type1");
			}
		}
		
		System.out.println(jedis.smembers("room1"));
		System.out.println(jedis.smembers("room2"));
		System.out.println(jedis.keys("room*"));
		
	}
	
	public static Map<String, Object> aa(Map<String, Object> aa) {
		System.out.println(aa);
		return aa;
	}
	
	public static testInteger bb(testInteger i) {
		System.out.println(i.num);
		return i;
	}
}

class testInteger {
	public int num = 1;
}