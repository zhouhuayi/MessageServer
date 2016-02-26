package com.choose.Message.mapper;

import java.util.List;
import java.util.Map;

import com.choose.Message.util.Params;

/**
 * 通用数据操作层接口
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public interface CommonMapper {
	/**添加*/
	public int add(Params params);
	
	/**修改*/
	public int update(String sql);
	
	/**删除*/
	public int delete(String sql);
	
	/**查询单条数据*/
	public Map<String, Object> getMapClass(String sql);
	
	/**查询多条数据*/
	public List<Map<String, Object>> getMapListClass(String sql);
	
	/**查询数量*/
	public long getCount(String sql);
	
	/**获取单个值得集合*/
	public List<Long> getListObject(String sql);
	
	/**获取单个值*/
	public Object getOneValue(String sql);
	
	
	/**查询多条数据*/
	public List<Map<String, Object>> getMapListClass1(Params params);
	
	/**添加*/
	public int add1(Params params);
	
	/**修改*/
	public int update1(Params params);
	
	/**删除*/
	public int delete1(Params params);
	
	/**查询单条数据*/
	public Map<String, Object> getMapClass1(Params params);
	
	/**查询数量*/
	public long getCount1(Params params);
	
	/**获取单个值*/
	public List<Long> getListObject1(Params params);
	
	/**获取单个值*/
	public String getOneValue1(Params params);
	
	/**获取所有数据不分页*/
	public List<Map<String, Object>> getListMap1(Params params);
}
