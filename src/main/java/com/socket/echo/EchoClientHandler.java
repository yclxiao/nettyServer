package com.socket.echo;

import com.socket.echo.util.Utils;
import io.netty.channel.*;

/**
 * Created by zhangjinye on 2017/11/26.
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<Object> {

    //当被通知channel是活跃的时候 发送一条消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //channel的生命周期
        //Channel channel= ctx.channel();
//        ctx.writeAndFlush("this is client\r\n");
//        ctx.writeAndFlush("7BAB\r\n");

//        ctx.writeAndFlush("7B8900183133393132333435363738010300000002C40B7B");
//        ctx.writeAndFlush("7B090019313339313233343536373801030402EA00A25BC67B\r\n");

        ctx.writeAndFlush("7B090019313339313233343536373801030402EA00A25BC67B");//发送的16进制字符串

//        byte[] queryWSByte=new byte[]{123,-127,0,16,49,51,57,49,50,51,52,53,54,55,56,1,3,0,0,0,2,-60,11,123};
//        byte[] byte11=new byte[]{123,9,0,25,49,51,57,49,50,51,52,53,54,55,56,1,3,4,2,-22,0,-94,91,-58,123};

//        String obj = Utils.str2HexStr("7B090019313339313233343536373801030402EA00A25BC67B");
//        String obj = Utils.bytesToHexString(byte11);
//        ctx.writeAndFlush(obj);
    }

    //读取消息
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端读取消息为:" + msg);

        //此处为测试代码，客户端持续发送消息给服务端
        Thread.sleep(1000);
        ctx.writeAndFlush("7B090019313339313233343536373801030402EA00A25BC67B");//发送的16进制字符串
    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//
//        System.out.println("客户端读取消息为:"+msg);
//    }

    //异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }
}
