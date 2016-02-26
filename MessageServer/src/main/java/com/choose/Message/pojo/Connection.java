package com.choose.Message.pojo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.choose.Message.bean.Room;

import io.netty.channel.group.ChannelGroup;

/**
 * 链接存储类
 *@author 吴耀宗
 *@data 2016/2/21
 */
public class Connection {	
	/***
	* 存储所有连接(用于多终端发送消息)
	* Map<用户ID(用户),Map<连接类型，连接信息>>
	*/
	public static volatile Map<String,Map<String,ClientType> > nodeCheck = new ConcurrentHashMap<String, Map<String,ClientType>>();
	/**
	 * 存储房间的链接
	 * Map<房间ID,链接组>
	 */
	public static volatile Map<String, ChannelGroup> roomMap = new HashMap<String, ChannelGroup>(); 
	/***
	 * 房间信息
	 * Map<房间id,房间信息>
	 */
	public static volatile Map<String,Room> room=new HashMap<String, Room>();
	/***
	 * 登陆所有连接
	 * 根据连接ID查找用户
	 * 用与多终端操作（发送消息）
	 * map<连接ID,用户> 
	 */
	public static Map<String,RoomBean> roomUserList = new HashMap<String,RoomBean>();
	
}
