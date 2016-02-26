package com.choose.Message.WebSocket;
import java.util.logging.Logger;

import com.choose.Message.WebSocket.handler.ChildChannelHandler;
import com.choose.Message.util.Configure;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/***
 *  启动WebSocket协议
 * @author 吴耀宗
 * @date 2016/2/21
 * @version 2.0
 * @TODO QQ:1849429934
 */
public class WebSocketMain {
	private FlashPolicyServer fpServer; 
	public static void main(String[] args) {
		InitServer init=new InitServer();
		InitServer.initSpring();//加载spring
		InitServer.initRedis();//加载redis
		init.initRoom();//初始化房间
		Logger.getLogger("开启服务类").info("***********初始化加载完毕***********");
		new WebSocketMain().run();//开启服务
	}
	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			fpServer=new FlashPolicyServer(843);         
			fpServer.start(); 
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChildChannelHandler());
			Channel ch = b.bind(Configure.address, Configure.websocketPost).sync().channel();
			Logger.getLogger("开启服务类").info("***********服务器里开启等待客户端连接***********");
			ch.closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
}