package com.gaolong.tageverything.nio.gchat;

public class GChatTest {

    public static void main(String[] args) {
        GChatServer gChatServer = new GChatServer(9999);
        gChatServer.start();

        GChatClient gChatClient = new GChatClient("127.0.0.1", 9999);
        gChatClient.start();


    }
}
