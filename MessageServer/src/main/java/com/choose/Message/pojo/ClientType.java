package com.choose.Message.pojo;

import io.netty.channel.Channel;

/***
 * 连接对象
 * 
 * @author d
 */
public class ClientType {
	private Channel channel;// 连接
	private String type;// 连接类型（IOS(2),android(1),网页(0),PC(3)）
	private String messageType;//消息类0型 1.websocket 2.private
	public ClientType(){}
	public ClientType(Channel channel, String type, String messageType) {
		super();
		this.channel = channel;
		this.type = type;
		this.messageType = messageType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

}
