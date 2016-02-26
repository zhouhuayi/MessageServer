package com.choose.Message.service;

import java.util.List;

import com.choose.Message.bean.Room;

/**
 *房间相关操作服务层
 *@author 吴耀宗
 *@data 2016/2/21
 */
public interface RoomService {
	/**
	 * 查找所有房间
	 * @return List<Room> 房间列表
	 */
	public List<Room> findAll();
}
