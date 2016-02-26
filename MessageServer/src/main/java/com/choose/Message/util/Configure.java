package com.choose.Message.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/***
 * 常用配置
 * @author d
 *
 */
public class Configure {
	/**
	 * 获取本地ip
	 * @return
	 */
	public static String getMyIp() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "127.0.0.1";
		}     
	}
	
	/**端口*/
	public static int websocketPost=18281;
	/**地址*/
	public static String address=getMyIp();
	//public static String address=getMyIp();
	public static int yk=1;
	
	

}
