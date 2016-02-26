package com.choose.Message.WebSocket.process;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.choose.Message.WebSocket.InitServer;
import com.choose.Message.service.PrivateMessageService;
import com.choose.Message.service.PublicMessageService;
import com.choose.Message.service.UserService;

import io.netty.channel.ChannelHandlerContext;

/**
 *Websocket消息处理中心
 *@author 吴耀宗
 *@date 2016/2/22
 */
public class MessageCenter {
	/**用户操作服务类*/
	public static UserService userService = InitServer.context.getBean(UserService.class);
	
	private static final Logger logger = Logger.getLogger(MessageCenter.class.getName());
	
	public static JSONObject setMessage(String msg, ChannelHandlerContext ctx) {
		msg=msg.replaceAll("\\s*", "");//空白替换
		JSONObject jsonObject = new JSONObject(msg);
		
		//返回响应信息
		JSONObject resultJson = new JSONObject();
		
		switch(jsonObject.get("type").toString()) {
			case "login":
				UserOperation.userLogin(jsonObject,ctx);
				break;
			case "Logout" : 
				break;
			case "privateChat" :
				PrivateMessageService privateChat = InitServer.context.getBean(PrivateMessageService.class);
				resultJson = privateChat.sendMessage(jsonObject);
				System.out.println(resultJson);
				break;
			case "publicChat" :
				PublicMessageService publicChat = InitServer.context.getBean(PublicMessageService.class);
				resultJson = publicChat.sendMessage(jsonObject);
				break;
			default:
				logger.info("暂未开通此功能！");
				break;
		}
		
		return resultJson;
	}
}
