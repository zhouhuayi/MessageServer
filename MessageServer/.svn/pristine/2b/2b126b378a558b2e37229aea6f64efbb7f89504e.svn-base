package com.choose.Message.WebSocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;

import org.json.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
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

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<Object> {

    public static Map<String, Channel> channelIdMap = new HashMap<String, Channel>();
    
//    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/choose/Message/resource/spring-mybatis.xml");
    
    private WebSocketServerHandshaker handshaker;
    
    private static final Logger logger = Logger.getLogger(WebSocketFrameHandler.class.getName());
    
    /**
     * 接受客服端发送的消息
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception { // (1)
    	
    	if(msg instanceof FullHttpRequest) {	//Http接入
    		System.out.println("http请求");
    		handleHttpRequest(ctx, (FullHttpRequest)msg);
    	} else if (msg instanceof WebSocketFrame) {	//webSocket接入
    		System.out.println("webSocket请求");
    		handleWebSocketFrame(ctx, (WebSocketFrame) msg);
    	} else {	//私有协议接入
    		System.out.println("私有协议请求");
    	}
    }

    /**
     * 加入时的系统消息
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        Iterator<String> it = channelIdMap.keySet().iterator();
        while (it.hasNext()) {
			String key = it.next();
			channelIdMap.get(key).writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress() + "[系统消息] - 游客" + incoming.id() + " 加入"));
		}
        channelIdMap.put(ctx.channel().id().toString(), ctx.channel());
    }

    /**
     * 离开时的系统消息
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        Iterator<String> it = channelIdMap.keySet().iterator();
        while (it.hasNext()) {
			String key = it.next();
			channelIdMap.get(key).writeAndFlush(new TextWebSocketFrame("[SERVER] - " + incoming.remoteAddress() + " 离开"));
		}
        channelIdMap.remove(ctx.channel().id().toString());
    }

    /**
     * 激活时服务端接受到的消息
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        System.out.println(ctx.channel().remoteAddress()+"游客" + ctx.channel().id() + "上线，当前在线人数为：" + channelIdMap.size());
    }

    /**
     * 没有连接时
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
    	System.out.println(ctx.channel().remoteAddress() + "游客" + ctx.channel().id() + "离线，当前在线人数为：" + channelIdMap.size());
    }

    /**
     * 连接异常时
     * 
     * @author 周化益
	 * @param ctx 渠道头部文本
	 * @param cause 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        Channel incoming = ctx.channel();
        System.out.println("Client:"+incoming.remoteAddress()+"异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
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
    
    /**
     * 连接成功
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    	ctx.flush();
    }
    
    /**
     * http接入处理方法
     * 
     * @author 周化益
     * @param ctx 渠道头部文本
     * @param req 消息体
     */
    public void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
    	//如果http解码失败，返回http异常
    	if(!req.getDecoderResult().isSuccess() || (!req.headers().get("Upgrade").equals("websocket"))) {
    		//设置响应消息
    		sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
    		return;
    	}
    	
    	//构建握手响应返回，本机测试
    	WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://192.168.0.11:8095", null, false);
    	
    	handshaker = wsFactory.newHandshaker(req);
    	
    	if(handshaker == null) {
    		WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
    	} else {
    		handshaker.handshake(ctx.channel(), req);
    	}
    }
    
    /**
     * webSocket协议
     * 
     * @author 周化益
     * @param ctx 渠道头部文本
     * @param msg 消息体
     */
    public void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
    	//是否是关闭连接指令
    	if(frame instanceof CloseWebSocketFrame) {
    		handshaker.close(ctx.channel(), (CloseWebSocketFrame)frame.retain());
    		return;
    	}
    	
    	//判断是否是ping消息
    	if(frame instanceof PingWebSocketFrame) {
    		ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
    		return;
    	}
    	
    	//
    	if(!(frame instanceof TextWebSocketFrame)) {
    		throw new UnsupportedOperationException("不支持二进制消息");
    	}
    	
    	Channel incoming = ctx.channel();
        String content = ((TextWebSocketFrame)frame).text();
        JSONObject json = null;
        
        System.out.println(content);
        
//        try {
//			json = new JSONObject(content);
//		} catch (Exception e) {
//			incoming.writeAndFlush(new TextWebSocketFrame("出入的格式不正确！"));
//			return;
//		}
        
//    	String method = json.remove("method").toString();
//    	
//    	if(method.equals("login")) {
//    		applicationContext.getBean(AccountService.class).loginAccount(json.getString("account"), json.get("password").toString());
//    		incoming.writeAndFlush(new TextWebSocketFrame("登陆成功！"));
//    	} else if(method.equals("register")) {
//    		Map<String, Object> map = BeanConvertMap.jsonToMap(json);
//    		applicationContext.getBean(AccountService.class).addAccount(map);
//    	} else {
    		/*私聊*/
    		if(content.contains("@")) {
    			String[] text = content.split("@");
    			channelIdMap.get(text[1]).writeAndFlush(new TextWebSocketFrame("[游客" + incoming.id() + "]私聊你：" + text[2]));
    			incoming.writeAndFlush(new TextWebSocketFrame("[自己]" + content));
    		} else {/*公聊*/
    			Iterator<String> it = channelIdMap.keySet().iterator();
    			while (it.hasNext()) {
    				String key = it.next();
    				if (key.equals(incoming.id().toString())) {
    					channelIdMap.get(key).writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress() + "[自己]" + content));
    				} else {
    					channelIdMap.get(key).writeAndFlush(new TextWebSocketFrame(ctx.channel().remoteAddress() + "[游客" + incoming.id() + "]" + content));
    				}
    			}
    		}
//    	}`
    }
    
    /**
     * 设置返回头消息
     * 
     * @author 周化益
     * @param ctx 渠道头部文本
     * @param req request请求
     * @param res response响应
     */
    public static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
    	//返回应答给客户端
    	if(res.getStatus().code() != 200) {
    		ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8);
    		res.content().writeBytes(buf);
    		buf.release();
    	}
    	
    	// 如果非Keep-Alive，关闭连接
    	ChannelFuture f = ctx.channel().writeAndFlush(res);
    	
		if (!isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		} else {
			// 构建http请求
            res.headers().set(HttpHeaders.Names.HOST, "8095");
            res.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
            res.headers().set(HttpHeaders.Names.CONTENT_LENGTH, res.content().readableBytes());
		}
	}
    
    /**
     * 判断连接是否保持激活状态
     * 
     * @author 周化益
     * @param req request请求
     * @return
     */
	private static boolean isKeepAlive(FullHttpRequest req) {
		return HttpHeaders.isKeepAlive(req);
	}
    
}