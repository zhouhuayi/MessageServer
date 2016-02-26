package com.choose.Message.pojo;

import java.util.List;
import java.util.Map;

/**
 * 消息批量添加pojo映射类
 * 
 * @author 周化益
 *
 */
public class MessagePojo {
	//存储批量添加的数据
	private List<Map<String, Object>> addListMap;
	
	public List<Map<String, Object>> getAddListMap() {
		return addListMap;
	}

	public void setAddListMap(List<Map<String, Object>> addListMap) {
		this.addListMap = addListMap;
	}
	
	
}
