package com.choose.Message.WebSocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class WebsocketChatServerInitializer extends ChannelInitializer<SocketChannel> { 

    @Override
    public void initChannel(SocketChannel ch) throws Exception {//2
        ChannelPipeline pipeline = ch.pipeline();
         
        pipeline.addLast("http-client", new IdleStateHandler(10, 15, 20));
        pipeline.addLast("http-codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(64*1024));
        pipeline.addLast("http-chunked", new ChunkedWriteHandler());
//        pipeline.addLast(new HttpRequestHandler("/ws"));
//        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast("handler", new WebSocketFrameHandler());

    }
}