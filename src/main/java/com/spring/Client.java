package com.spring;

import com.socket.echo.EchoClient;

public class Client {
    public static void main(String[] args){
        EchoClient echoClient = new EchoClient(16000,"127.0.0.1");
        try {
            echoClient.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
