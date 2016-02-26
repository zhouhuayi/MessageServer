package com.choose.Message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.choose.Message.bean.Room;
import com.choose.Message.mapper.RoomMapper;
import com.choose.Message.service.RoomService;
@Service
public class RoomServiceImpl implements  RoomService{
	@Resource
	private RoomMapper roomMapper;
	public List<Room> findAll() {
		return roomMapper.findAll();
	}
}
