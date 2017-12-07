package com.spring.io.path;

import com.socket.echo.EchoServer;

/**
 * Created by zhangjinye on 2017/11/26.
 */
public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        //启动server
        EchoServer echoServer=new EchoServer(8888);
        echoServer.start();
    }
}
