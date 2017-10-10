package com.lgz.framwork.netty.withSpringBoot;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * Created by ligaozhao on 05/10/17.
 */
@Component
@Qualifier("springChannelInitializer")
public class SpringChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Autowired
    private StringDecoder stringDecoder;
    @Autowired
    private StringEncoder stringEncoder;
    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", stringDecoder);
        pipeline.addLast("handler", nettyServerHandler);
        pipeline.addLast("encoder", stringEncoder);
    }
}
