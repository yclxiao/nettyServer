package com.spring;

import com.socket.echo.EchoServer;

/**
 * 整理采用netty框架
 * 客户端发送  16进制字符串对应的byte数组到服务端
 * 服务端接收到byte数组后，再把16进制数组后，转成16进制字符串，然后截取xxx位数，再把截取出来的16进制转成10进制
 *
 * idea打jar包的：https://www.cnblogs.com/blog5277/p/5920560.html
 */
public class Server {
    public static void main(String[] args){
        EchoServer echoServer = new EchoServer(16000);
        try {
            echoServer.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
