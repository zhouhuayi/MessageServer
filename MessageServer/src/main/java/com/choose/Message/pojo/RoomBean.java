package com.choose.Message.pojo;

import java.util.Map;

/**
 * 房间成员信息
 * @author d
 *
 */
public class RoomBean {
	public RoomBean(){};
	public RoomBean(Map<String,Object> roomMap,Map<String,Object> userMap){
		this.roomMap=roomMap;
		this.userMap=userMap;
	};
	private Map<String,Object> roomMap;
	private Map<String,Object> userMap;
	/**
	 * 登录的用户信息
	 * @return
	 */
	public Map<String,Object> getUserMap() {
		return userMap;
	}
	public void setUserMap(Map<String,Object> userMap) {
		this.userMap = userMap;
	}
	
	/**
	 * 用户所在房间信息(null就表示未在房间)
	 * @return
	 */
	public Map<String,Object> getRoomMap() {
		return roomMap;
	}
	public void setRoomMap(Map<String,Object> roomMap) {
		this.roomMap = roomMap;
	}
	
	
}
