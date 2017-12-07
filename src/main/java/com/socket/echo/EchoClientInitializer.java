package com.socket.echo;

import com.socket.echo.coder.MsgDecoder;
import com.socket.echo.coder.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * Created by zhangjinye on 2017/11/26.
 */
public class EchoClientInitializer extends ChannelInitializer<SocketChannel> {

    //配置通道
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 以("\n")为结尾分割的 解码器，不加这个解码器，则不用发送\n
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));

        // 字符串解码 和 编码
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());


        pipeline.addLast("decoder",new MsgDecoder());
        pipeline.addLast("encoder",new MsgEncoder());

        pipeline.addLast(new EchoClientHandler());
    }
}
