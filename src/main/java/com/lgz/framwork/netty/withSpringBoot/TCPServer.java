package com.lgz.framwork.netty.withSpringBoot;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * Created by ligaozhao on 05/10/17.
 */
@Service
public class TCPServer {
    @Autowired
    @Qualifier("bootstrap")
    private ServerBootstrap bootstrap;

    @Autowired
    @Qualifier("tcpPort")
    private InetSocketAddress tcpPort;

    private ChannelFuture serverChannelFuture;

    @PostConstruct
    public void start() throws Exception {
        System.out.println("Starting server at " + tcpPort.getHostName() + " : " + tcpPort.getPort());
        serverChannelFuture = bootstrap.bind(tcpPort).sync();
    }

    @PreDestroy
    public void stop() throws Exception {
        serverChannelFuture.channel().closeFuture().sync();
    }
}
