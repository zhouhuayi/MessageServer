package com.choose.Message.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

/**
 * 常用方法工具类
 * 
 * @version 1.0
 * @author 周化益
 * @since 2016-02-15
 */
public class CommonUtil {

	/**
	 * 文件读取路径
	 * 
	 * @author 周化益
	 * @param entityClass
	 *            实体Class
	 * @param entityId
	 *            实体ID
	 * @param fileName
	 *            文件名
	 */
	public static <T> String readFilePath(Class<T> entityClass, long entityId,
			String fileName) {
		String path = "upload/" + entityClass.getSimpleName() + "/" + entityId
				+ "/" + fileName;
		return path;
	}

	/**
	 * String转List<T>
	 * 
	 * @author 周化益
	 * @param type
	 *            List的类型
	 * @param str
	 *            传入的字符串
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> strToList(Class<T> type, String str) {
		List<T> list = null;
		try {
			if (str.trim().length() > 1) {
				Object[] strArray = str.split(",");
				List<Object> strList = new ArrayList<Object>();
				strList = Arrays.asList(strArray);
				list = new ArrayList<T>();
				for (Object obj : strList) {
					list.add((T) obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 清除临时文件
	 * 
	 * @author 周化益
	 * @param filePath
	 *            临时文件所属目录
	 */
	public static void clearFile(String filePath) {
		File temf = new File(uploadPath() + filePath);
		temf.delete();
	}

	/**
	 * 清除临时文件
	 * 
	 * @author 周化益
	 * @param filePath
	 *            临时文件所属目录
	 */
	public static void clearTemFile(String filePath) {
		File temf = new File(uploadPath() + filePath);
		File[] fs = temf.listFiles();
		for (int i = 0; i < fs.length; i++) {
			fs[i].delete();
		}
	}

	/**
	 * 获取项目部署路径
	 * 
	 * @author 周化益
	 * @return 返回项目部署后的路径
	 */
	public static String uploadPath() {
		String path = CommonUtil.class.getResource("").getPath();
		int end = path.lastIndexOf("WEB-INF");
		path = path.substring(1, end);
		return path + "/";
	}

	// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
	private static int BEGIN = 45217;
	private static int END = 63486;

	// 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
	// i, u, v都不做声母, 自定规则跟随前面的字母
	private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈',
			'哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌',
			'塌', '挖', '昔', '压', '匝', };

	// 二十六个字母区间对应二十七个端点
	// GB2312码汉字区间十进制表示
	private static int[] table = new int[27];

	// 对应首字母区间表
	private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			't', 't', 'w', 'x', 'y', 'z', };

	// 初始化
	static {
		for (int i = 0; i < 26; i++) {
			table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
		}
		table[26] = END;// 区间表结尾
	}

	// ------------------------public方法区------------------------
	// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出

	public static String cn2py(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Initial(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
			e.printStackTrace();
		}
		return Result.toUpperCase();
	}

	// ------------------------private方法区------------------------
	/**
	 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　* 　　
	 */
	private static char Char2Initial(char ch) {
		// 对英文字母的处理：小写字母转换为大写，大写的直接返回
		if (ch >= 'a' && ch <= 'z') {
			return (char) (ch - 'a' + 'A');
		}
		if (ch >= 'A' && ch <= 'Z') {
			return ch;
		}
		// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
		// 若不是，则直接返回。
		// 若是，则在码表内的进行判断。
		int gb = gbValue(ch);// 汉字转换首字母
		if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
		{
			return ch;
		}
		int i;
		for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
			if ((gb >= table[i]) && (gb < table[i + 1])) {
				break;
			}
		}
		if (gb == END) {// 补上GB2312区间最右端
			i = 25;
		}
		return initialtable[i]; // 在码表区间中，返回首字母
	}

	/**
	 * 取出汉字的编码 cn 汉字 　　
	 */
	private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 根据code获取股票json数据
	 * 
	 * @author 周化益
	 * @param code 股票code
	 * @return
	 */
	public static JSONObject cc(String code) {
		StringBuffer temp = new StringBuffer();
		JSONObject searchJson = null;
		try {
			// String url = "http://www.baidu.com/jiaojing/ser.php";
			String url = "http://htmmarket.fx678.com/list.php"
					+ "?excode=SZPEC&time=1428999404&token=310242a4068f1cad5096aec31a774f6c"
					+ "&key=9d5aedcbe0558ac731507197cc9965eb";
			System.out.println(url);
			HttpURLConnection uc = (HttpURLConnection) new URL(url)
					.openConnection();
			uc.setConnectTimeout(10000);
			uc.setDoOutput(true);
			uc.setRequestMethod("POST");
			uc.setUseCaches(false);
			DataOutputStream out = new DataOutputStream(uc.getOutputStream());
			out.flush();
			out.close();
			InputStream in = new BufferedInputStream(uc.getInputStream());
			Reader rd = new InputStreamReader(in, "UTF-8");
			int c = 0;
			while ((c = rd.read()) != -1) {
				temp.append((char) c);
			}

			// System.out.println(temp.toString());
			String[] jsonMap = temp.toString().split("},");
			int jsonLeng = jsonMap.length;
			List<JSONObject> jsonStrList = new ArrayList<JSONObject>();

			for (int i = 0; i < jsonLeng; i++) {
				String json;
				if (i == 0) {
					continue;
				} else if (i == jsonLeng - 1) {
					json = (jsonMap[i].substring(0, jsonMap[i].length() - 1));
				} else {
					json = jsonMap[i] + "}";
				}
				jsonStrList.add(new JSONObject(json));
			}

			for (JSONObject jsonObject : jsonStrList) {
				if (jsonObject.get("Code").equals(code)) {
					searchJson = jsonObject;
				}
			}
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchJson;
	}

	/**
	 * MD5加码 生成32位md5码
	 * 
	 * @author 周化益
	 * @param inStr 传入的字符串
	 * @return 加密后的字符串
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = (md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		
		return hexValue.toString();
	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 * 
	 * @author 周化益
	 * @param inStr 传入的字符串
	 */
	public static String convertMD5(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;
	}
}