package com.glt.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by gaolong on 2016/12/5.
 */
public class SimpleServer {

    private int port;

    public SimpleServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        // EventLoopGroup 是用来处理IO操作的多线程时间循环器
        // bossGroup 用来接收近来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // workerGroup 用来处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    // 配置 Channel
                    .channel(NioServerSocketChannel.class)// todo ?
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new com.glt.netty.SimpleServerHandler());
                        }
                    })

                    .option(ChannelOption.SO_BACKLOG, 128)// todo ?
                    .childOption(ChannelOption.SO_KEEPALIVE, true);// todo ?

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new SimpleServer(9999).run();
    }

}
