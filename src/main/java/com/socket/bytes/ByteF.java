package com.socket.bytes;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * Created by zhangjinye on 2017/11/30.
 */
public class ByteF {

    public void buffer1(){
        ByteBuf buffer= Unpooled.buffer(1024);
        buffer.writeBytes("hello world!".getBytes());
        //cap
//        int cap=buffer.capacity();
//        for (int i=0;i<cap;i++){
//            byte b=buffer.getByte(i);
//            System.out.println((char)b);
//        }
        //获取某个位数
        System.out.println((char) buffer.getByte(1));
        //写字节数
        System.out.println(buffer.writableBytes());
        //读
        System.out.println(buffer.readableBytes());
        //System.out.println(buffer.capacity());
        //System.out.println(new String(buffer.array()));
    }

    public void copy(){
        ByteBuf buf=Unpooled.copiedBuffer("hello中国!", CharsetUtil.UTF_8);
        //获取索引
        System.out.println((char)buf.getByte(0));
        //读写索引
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
        //修改值
        buf.setByte(0,'b');
        System.out.println((char)buf.getByte(0));
        //查询读写索引值
        System.out.println();
        System.out.println(buf.readerIndex());
        System.out.println(buf.writerIndex());
    }

    public void pool(){
        PooledByteBufAllocator.defaultNumHeapArena();
    }
}
