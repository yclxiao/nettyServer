package com.socket.echo.coder;

import com.socket.echo.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class MsgEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object object, ByteBuf out) throws Exception {

        /*byte[] tempBytes = Utils.hexStringToByte((String) object);

        byte[] target = new byte[tempBytes.length + 1];

        for (int i = 0;i<tempBytes.length;i++){
            target[i] = tempBytes[i];
        }
        target[tempBytes.length] = 10; //此处有个小坑，在把原始的16进制字符串转成byte数组之后，继续追加一个"\n"对应的10进制10进去，这样服务端才能接受消息

        out.writeBytes(target);*/


        byte[] target = Utils.hexStringToByte((String) object);//16进制字符串转成byte数组
        out.writeBytes(target);

    }
}
