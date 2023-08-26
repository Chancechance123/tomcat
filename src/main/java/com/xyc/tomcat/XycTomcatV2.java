package com.xyc.tomcat;

import com.xyc.tomcat.handler.XycRequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public class XycTomcatV2 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("=========  XycTomcatV2在8080端口监听 ==============");
        //只要socket端口没有关闭  就一直等待浏览器/客户端连接
        while (!serverSocket.isClosed()){
            Socket socket = serverSocket.accept();
            new Thread(new XycRequestHandler(socket)).start();
        }
    }
}
