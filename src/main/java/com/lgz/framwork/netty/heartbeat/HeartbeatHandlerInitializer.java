package com.lgz.framwork.netty.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created by ligaozhao on 04/10/17.
 */
public class HeartbeatHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     * Netty 的超时类型 IdleState 主要分为：
     * <p>
     * ALL_IDLE : 一段时间内没有数据接收或者发送
     * READER_IDLE ： 一段时间内没有数据接收
     * WRITER_IDLE ： 一段时间内没有数据发送
     * <p>
     * 在 Netty 的 timeout 包下，主要类有：
     * IdleStateEvent ： 超时的事件
     * IdleStateHandler ： 超时状态处理
     * ReadTimeoutHandler ： 读超时状态处理
     * WriteTimeoutHandler ： 写超时状态处理
     */

    private static final int READ_IDEL_TIME_OUT = 4; // 读超时
    private static final int WRITE_IDEL_TIME_OUT = 5;// 写超时
    private static final int ALL_IDEL_TIME_OUT = 7; // 所有超时

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(READ_IDEL_TIME_OUT, WRITE_IDEL_TIME_OUT, ALL_IDEL_TIME_OUT, TimeUnit.SECONDS)); // 1
        pipeline.addLast(new HeartbeatServerHandler()); // 2
    }
}
