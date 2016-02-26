package com.choose.Message.WebSocket.handler;
import java.util.logging.Logger;

import org.json.JSONObject;

import com.choose.Message.WebSocket.process.MessageCenter;
import com.choose.Message.pojo.Connection;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.ImmediateEventExecutor;
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	public static final Logger logger = Logger
			.getLogger(WebSocketServerHandshaker.class.getName());
			private WebSocketServerHandshaker handshaker;
	/***
	 * 每当从服务端收到新的客户端连接时，
	 * 客户端的 Channel存入 ChannelGroup列表中，并通知列表中的其他客户端 Channel
	 */
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		//1.
		//Channel incoming = ctx.channel();
		//ServerData.group.add(incoming);
		//System.out.println("所有链接数:"+ServerData.group.size());
	}

	/***
	 * 服务端监听到客户端活动
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//2
		Channel incoming = ctx.channel();
		// 添加
		logger.info("客户端与服务端连接开启");
		logger.info("Client:" + incoming.remoteAddress() + "在线");

	}

	/***
	 * 服务端监听到客户端不活动
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("客户端与服务端连接关闭");
	}

	/***
	 * 每当从服务端读到客户端写入信息时，将信息转发给其他客户端的 Channel
	 */
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//3
		logger.info(msg.toString());
		//传统的http接入
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, ((FullHttpRequest) msg));
			logger.info("HttpRequest连接服务器");
		//websocket接入	
		} else if (msg instanceof WebSocketFrame) {
			logger.info("WebSocket消息");
			handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
		}

	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		//5.
		ctx.flush();
	}

	private void handlerWebSocketFrame(ChannelHandlerContext ctx,
			WebSocketFrame frame) {
		//6.
		// 判断是否关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(),
					(CloseWebSocketFrame) frame.retain());
			logger.info("客户端发送关闭链路的指令");
			return;
		}

		// 判断是否ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(
					new PongWebSocketFrame(frame.content().retain()));
			return;
		}

		// 本例程仅支持文本消息，不支持二进制消息
		if (!(frame instanceof TextWebSocketFrame)) {
			logger.info("本例程仅支持文本消息，不支持二进制消息");
			ctx.close();
//			throw new UnsupportedOperationException(String.format(
//					"%s frame types not supported", frame.getClass().getName()));
		}else{
			// 返回应答消息
			String request = ((TextWebSocketFrame) frame).text();
			logger.info("服务端收到：" + request);
		
			try {
				 MessageCenter.setMessage(request,ctx);
			} catch (Exception e) {
				//接受的格式不正确
				String msg="{\"type\":\"allFailMsg\"}";
				TextWebSocketFrame twx=new TextWebSocketFrame(msg);
				ctx.channel().writeAndFlush(twx);
			}
		}
	}
	
	/** 
	 * 监听链接读写超时操作
	 * @author 吴耀宗
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE){
				String msg="{\"type\":\"ping\"}";
				logger.info("服务端发送心跳消息");
				TextWebSocketFrame twx=new TextWebSocketFrame(msg);
				ctx.channel().writeAndFlush(twx);
			}
			else if (event.state() == IdleState.WRITER_IDLE){
				
			}
			else if (event.state() == IdleState.ALL_IDLE){
				String msg="{\"type\":\"ping\"}";
				logger.info("服务端发送心跳消息");
				TextWebSocketFrame twx=new TextWebSocketFrame(msg);
				ctx.channel().writeAndFlush(twx);
			}
		}
	}
	/***
	 * 连接服务器
	 * @param ctx
	 * @param req
	 */
	private void handleHttpRequest(ChannelHandlerContext ctx,
			FullHttpRequest req) {
				
		String upgrade=(req.headers().get("Upgrade")).toLowerCase();

		//如果http解码失败，返回http异常
		if (!req.getDecoderResult().isSuccess()
				|| (!"websocket".equals(upgrade))) {
			
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(
					HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			
			return;
		}
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				"ws://localhost:5069/websocket", null, false);

		handshaker = wsFactory.newHandshaker(req);

		if (handshaker == null) {
			WebSocketServerHandshakerFactory
					.sendUnsupportedWebSocketVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}                       

	}

	private static void sendHttpResponse(ChannelHandlerContext ctx,
			FullHttpRequest req, DefaultFullHttpResponse res) {
		logger.info("----------------返回应答给客户端----------------");
		// 返回应答给客户端
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
					CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
		}

		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
			logger.info("----------------非Keep-Alive，关闭连接----------------");
		}
	}

	/**
	 * 判断是否长连接
	 * 
	 * @author 周化益
	 * @param req 请求对象
	 * @return
	 */
	private static boolean isKeepAlive(FullHttpRequest req) {
		return HttpHeaders.isKeepAlive(req);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		leave(ctx);;
	}
	
	/**
     * 离开时的系统消息
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
    	leave(ctx);;
    }
	
    
	/**
	 * 断开连接方法
	 * 
	 * @author 周化益
	 * @param ctx 渠道头部文本
	 */
	public void leave(final ChannelHandlerContext ctx) {
		new Thread(new Runnable() {
			@Override 
			public void run() {
				//获取当前连接
		        Channel incoming = ctx.channel();
		        
		        //获取当前连接所在房间
		        ChannelGroup channeGroup = Connection.roomMap.get("10001");
		        
		        //把当前连接在房间中移除
		        channeGroup.remove(incoming);
		        
		        //获取当前连接所属用户ID
		        String userId = Connection.roomUserList.remove(incoming.id()).getUserMap().get("userId").toString();
		        
		        //获取用户的连接的数量
		        int connectionNum = Connection.nodeCheck.get(userId).size();
		        
		        if(connectionNum > 1) {	//删除用户的单个连接
		        	Connection.nodeCheck.get(userId).remove(incoming.id());
		        } else {	//删除整个用户的连接
		        	Connection.nodeCheck.remove(userId);
		        }
		        //房间群发提示信息
		        channeGroup.writeAndFlush(new TextWebSocketFrame("[系统] - " + incoming.remoteAddress() + " 离开"));
		        
		        /*统计在线时长*/
		        
		        incoming.close();
			}
		});
	}
}
