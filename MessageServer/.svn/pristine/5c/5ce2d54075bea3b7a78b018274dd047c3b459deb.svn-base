package com.choose.Message.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class DateStrConvert {
	/**
	 * 将日期转换成字符串
	 * 
	 * @author 周化益
	 * @param date 输入的如期
	 * @param format 转换的格式
	 * @return 日期转换后的字符串
	 */
	 public static String dateToStr(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String dateString = formatter.format(date);
		return dateString;
	 }
	 
		/**
		 * 将字符串转换成日期
		 * 
		 * @author 周化益
		 * @param dateString 输入的字符串
		 * @param format 转换的格式
		 * @return 字符串转换后的日期
		 */
	 public static Date strToDate(String dateString, String format) {
			DateFormat dataformatter = new SimpleDateFormat(format);
			Date date = null;
			try {
				date = dataformatter.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date;
	 }
	 
	 /**
	  * 测试方法
	  * 
	  * @author 周化益
	  * @param args
	  */
	 public static void main(String[] args) {
		String date = "2015-10-22";
		System.out.println(strToDate(date, "yyyy-MM-dd HH:mm:ss"));
	}
}
