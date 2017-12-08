package com.socket.echo;

import com.socket.echo.bean.DeviceMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.util.Date;

/**
 * Created by zhangjinye on 2017/11/26.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends SimpleChannelInboundHandler<Object> {

    private static boolean isLogin = false;

    //读取通道内容
//    @Override
//    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf in= (ByteBuf) msg;
//
//    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
//
//        System.out.println("服务器收到:"+msg);
//        ctx.writeAndFlush("hello server\r\n");
//
//    }


    //此处是通道激活，设备第一次连会进入此场景
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);


        //收到消息后，我这边是测试，还发送了同样的消息过去了，此次可以开启一个线程，定时发送消息给设备，比如心跳？或者定时获取湿度、温度
        //此处可以做判断，如果是登录消息，则咋样。。。如果是其他消息，则咋样。。。
        //都是直接发送16进制字符串就行了，编码器会自动转换的
//        ctx.writeAndFlush("7B090019313339313233343536373801030402EA00A25BC67B");

        //比如这是每隔5S发送心跳
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ctx.writeAndFlush("7B090019313339313233343536373801030402EA00A25BC67B");
                }
            }
        }).start();*/


        //比如这是每隔1小时去获取湿度、温度
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    try {
                        Thread.sleep(1000 * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (isLogin) {

                        //发送温度湿度
                        ctx.writeAndFlush("7B8900183133393132333435363738010300000002C40B7B");
                        System.out.println("temperature/humidity send end...");

                        try {
                            Thread.sleep(1000 * 20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //发送水表
                        ctx.writeAndFlush("7B890018313339313233343536373818030000000446007B");
                        System.out.println("waterMeter send end...");


                        try {
                            Thread.sleep(1000 * 60 * 60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }).start();

    }

    //通道读取完毕
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                .addListener(ChannelFutureListener.CLOSE); //此次一次消息读取完毕，不用发送空的关闭消息过去
        super.channelReadComplete(ctx);
    }

    //异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    //此处为收到设备的消息，收到的消息已经经过了msgdecoder解码了
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {

//        ByteBuf buf = (ByteBuf) msg;
//
//        byte[] req = new byte[buf.readableBytes()];
//
//        buf.readBytes(req);
//
//        String body = new String(req,"UTF-8");


//        System.out.println("服务器收到:" + msg);
//        ctx.writeAndFlush("hello server\r\n");


        DeviceMsg deviceMsg = (DeviceMsg) msg;

        int msgType = deviceMsg.getMsgType();
        int temperature = deviceMsg.getTemperature();
        int humidity = deviceMsg.getHumidity();
        String waterMeter = deviceMsg.getWaterMeter();

        if (msgType == 1){
            ctx.writeAndFlush("7B81001031333931323334353637387B");
            isLogin = true;

            System.out.println(new Date() + ",login response end...");

        } else if (msgType == 2){
            //TODO , 温度湿度入库
            System.out.println(new Date() + ",temperature:" + temperature + ",humidity:" + humidity + ",into db...");
        } else if (msgType == 3){
            //TODO , 水表入库
            System.out.println(new Date() + ",waterMeter:" + waterMeter + ",into db...");
        }

    }
}
