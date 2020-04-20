package com.gaolong.tageverything.nio.gchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class GChatServer {
    private int port;

    public GChatServer(int port) {
        this.port = port;
    }

    public void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = serverBootstrap.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new GChatServerChildChannelHandler());
                        }
                    })
                    .bind(port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (channelFuture != null) {
                try {
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static class GChatServerChildChannelHandler extends SimpleChannelInboundHandler<String> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
            System.out.println(msg);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(msg);
        }
    }
}
