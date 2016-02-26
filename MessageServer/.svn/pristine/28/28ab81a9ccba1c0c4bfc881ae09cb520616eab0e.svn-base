package com.choose.Message.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

/**
 * bean与map之间相互转换工具类
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class BeanConvertMap {
	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @author 周化益
	 * @param bean 要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 */
	public static Map<String, Object> convertBean(Object bean) {
		//获取传入的实体的Class
		Class<?> type = bean.getClass();
		
		//创建接受对象
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		//定义实体信息对象
		BeanInfo beanInfo;
		
		try {
			//获取实体详细信息
			beanInfo = Introspector.getBeanInfo(type);
			
			//获取实体属性描述集合
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				//获取属性描述
				PropertyDescriptor descriptor = propertyDescriptors[i];
				
				//获取属性名
				String propertyName = descriptor.getName();
				
				if (!propertyName.equals("class")) {
					//获取属性的读取方法
					Method readMethod = descriptor.getReadMethod();
					
					//通过反射获取该属性对应的值
					Object result = readMethod.invoke(bean, new Object[0]);
					
					/*判断是否为空，为空则赋值空字符串*/
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		
		return returnMap;
	}

	/**
	 * 将一个Map对象转化为一个JavaBean
	 * 
	 * @author 周化益
	 * @param map 包含属性值的map
	 * @param bean 要转化的类型
	 * @return beanObj 转化出来的JavaBean对象
	 */
	public static <T> T convertMap(Class<T> clazz, Map<String, Object> paramMap) {
		//定义返回的实体对象
		T beanObj = null;
		try {
			//初始化返回对象
			beanObj = clazz.newInstance();
			
			//定义属性名
			String propertyName = null;
			
			//定义属性值
			Object propertyValue = null;
			for (Map.Entry<String, Object> entity : paramMap.entrySet()) {
				//获取属性名
				propertyName = entity.getKey();
				
				//获取属性值
				propertyValue = entity.getValue();
				
				//给返回对象进行赋值
				setProperties(beanObj, propertyName, propertyValue);
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("不合法或不正确的参数", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("实例化JavaBean失败", e);
		} catch (Exception e) {
			throw new RuntimeException("Map转换为Java Bean对象失败", e);
		}

		return beanObj;
	}

	/**
	 * 给对象进行赋值
	 * 
	 * @author 周化益
	 * @param entity 赋值对象
	 * @param propertyName 属性名
	 * @param value 属性值
	 */
	private static <T> void setProperties(T entity, String propertyName, Object value) 
			throws IntrospectionException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		
		//获取属性描述
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,entity.getClass());
		
		//获取属性的赋值方法
		Method methodSet = pd.getWriteMethod();
		if (value == null) {
			methodSet.invoke(entity, value);
		} else {
			//将值进行类型转换
			value = typeConvert(pd.getReadMethod().getReturnType(), value);
			
			//通过映射将值赋值给返回实体
			methodSet.invoke(entity, value = value.equals("") ? null : value);
		}
	}

	/**
	 * 类型转换
	 * 
	 * @author 周化益
	 * @param typeClass 属性类型的class
	 * @param value 要转换的值
	 * @return 返回转换后的值
	 */
	public static Object typeConvert(Class<?> typeClass, Object value) {
		/*进行类型判断，并转换类型*/
		if (typeClass == int.class || typeClass == Integer.class) {
			return Integer.valueOf(value.toString()).intValue();
		} else if (typeClass == String.class) {
			return value.toString();
		} else if (typeClass == Float.class) {
			return Float.valueOf(value.toString()).floatValue();
		} else if (typeClass == long.class || typeClass == Long.class) {
			return Long.valueOf(value.toString()).longValue();
		} else if (typeClass == java.sql.Date.class) {
			if (value.getClass() == String.class) {
				return java.sql.Date.valueOf(value.toString());
			} else {
				return value;
			}
		} else if (typeClass == java.util.Date.class) {
			if (value.getClass() == String.class) {
				if(value.toString().length() > 10) {
					return DateStrConvert.strToDate(value.toString(), "yyyy-MM-dd HH:mm:ss");
				} else {
					return DateStrConvert.strToDate(value.toString(), "yyyy-MM-dd");
				}
			} else {
				return value;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * JSONObejct转换成Map数据
	 * 
	 * @author 周化益
	 * @param json JSONObject对象数据
	 * @return
	 */
	public static Map<String, Object> jsonToMap(JSONObject json) {
		Iterator<String> keys = json.keySet().iterator();
		Map<String, Object> map = new HashMap<String, Object>();
		while(keys.hasNext()) {
			String key = keys.next();
			map.put(key, json.get(key));
		}
		return map;
	}
	
	public static JSONObject mapToJson(Map<String, Object> map) {
		JSONObject json = new JSONObject(map);
		return json;
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aa", 123);
		map.put("bb", 222);
		
		System.out.println(mapToJson(map));
		String jsonTXT = "{\"aa\":123,\"bb\":222}";
		JSONObject json = new JSONObject(jsonTXT);
		
		System.out.println(jsonToMap(json));
	}
}
