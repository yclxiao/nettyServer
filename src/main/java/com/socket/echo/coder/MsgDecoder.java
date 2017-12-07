package com.socket.echo.coder;

import com.socket.echo.util.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MsgDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        /*int length = byteBuf.readableBytes();
        byte[] bytes = new byte[4];

        byteBuf.getBytes(36,bytes,0,4);
        String temp = new String(bytes,"UTF-8");
//        list.add(temp);
        list.add(temp);//此处list有几个对象，channelRead0就会执行几次*/

        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];

        byteBuf.getBytes(byteBuf.readerIndex(),bytes,0,length);

//        byte[] byte11=new byte[]{123,9,0,25,49,51,57,49,50,51,52,53,54,55,56,1,3,4,2,-22,0,-94,91,-58,123};

        String result = Utils.bytesToHexString(bytes);

        String obj = result.substring(36,40);//此处根据自己的规则截取即可

        int temp = Integer.parseInt(obj,16);//16进制转成10进制

        list.add(temp);
    }
}
