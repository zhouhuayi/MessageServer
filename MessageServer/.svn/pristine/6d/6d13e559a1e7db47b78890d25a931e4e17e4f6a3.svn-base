package com.choose.Message.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 控制层操作工具类型
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class UtilController {
	/**
	 * 获取IP地址
	 * 
	 * @author 周化益
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ipAddress;
	    if (request.getHeader("x-forwarded-for") == null) {
	    	 ipAddress = request.getRemoteAddr();
		} else {
			ipAddress = request.getHeader("x-forwarded-for");
		}
	    return ipAddress;
	}
	
	/**
	 * 获取当前Session中的用户数据
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getUserByRequest(HttpServletRequest request) {
		return (Map<String, Object>)request.getSession().getAttribute("user");
	}
	
	/**
	 * 获取服务器路径
	 * 
	 * @author 周化益
	 * @param request
	 * @return
	 */
	public static String getRealPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("");
	}
	
	/**
	 * 获取服务器路径
	 * 
	 * @author 周化益
	 * @param request
	 * @return
	 */
	public static String getFfmpegPath(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/WEB-INF/classes/resources/");
	}
}
