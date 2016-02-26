package com.choose.Message.WebSocket;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.choose.Message.bean.Room;
import com.choose.Message.pojo.Connection;
import com.choose.Message.service.RoomService;
import com.choose.Message.util.Redis;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import redis.clients.jedis.Jedis;
/***
 * 服务初始化信息
 * @author吴耀宗
 * @date 2016/2/21
 * @version 2.0
 * @TODO QQ:1849429934
 */
public class InitServer {
	public static Jedis jedis;//jeids使用对象
	public static ApplicationContext context;//spring加载
	public RoomService roomServer;
	/***
	 * 初始化房间
	 */
	public void initRoom() {
		try {
		roomServer = InitServer.context.getBean(RoomService.class);
		List<Room> list = roomServer.findAll();
			for (Room room : list) {
				ChannelGroup channelGroup = new DefaultChannelGroup(
						GlobalEventExecutor.INSTANCE);
				//添加房间所有连接
				Connection.roomMap.put(room.getId().toString(),channelGroup);
				//存储房间信息
				Connection.room.put(room.getId().toString(),room);
			}
		} catch (Exception e) {
			Logger.getLogger("初始化加载类:").info("初始化房间失败......");
			e.printStackTrace();
		}
		
	}
	/**
	 * 初始化加载spring配置文件
	 */
	public static void initSpring(){
		try {
		InitServer.context=new FileSystemXmlApplicationContext("classpath:com/choose/message/resource/spring-mybatis.xml");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger("初始化加载类:").info("spring文件加载失败.....");
		}		
	} 
	/**
	 * 初始化加载读取redis配置
	 */
	public static void initRedis(){
		try {
//			InitServer.jedis=new Jedis("192.168.0.33",6379);
//			jedis.auth("ycm12345");
			jedis = Redis.getJedis();
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger("初始化加载类:").info("redis链接失败....");
		}
		
	}
}
