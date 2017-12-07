package com.spring.io.path;

import com.socket.echo.EchoClient;

/**
 * Created by zhangjinye on 2017/11/26.
 */
public class TestClient {

    public static void main(String[] args) throws InterruptedException {
        //启动server
        EchoClient echoServer=new EchoClient(8888,"127.0.0.1");
        echoServer.start();
    }
}
