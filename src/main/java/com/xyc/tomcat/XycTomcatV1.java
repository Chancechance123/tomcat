package com.xyc.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author XYC
 * @version 1.0
 * @DAte 这是一个tomcat 完成 接受 浏览器的数据，并返回相关信息
 */
public class XycTomcatV1 {
    public static void main(String[] args) throws IOException {

        // 创建ServerSocket 在8080端口监听
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("=====mytomcat 在8080端口监听 ======");
        while (!serverSocket.isClosed()) {

            // 等待浏览器/客户端的连接
            // 如果有连接就创建一个socket对象
            // socket是浏览器/客户端的通道
            Socket socket = serverSocket.accept();

            // 先接受浏览器数据
            // 用bufferedReader来将数据从字节流转换成字符流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String mes = null;
            System.out.println("=====接受到浏览器发送的数据===================");
            // 循环读取 (按行)
            while ((mes = bufferedReader.readLine()) != null) {
                // 判断mes的长度
                if (mes.length() == 0) {
                    break;
                }
                System.out.println(mes);
            }
            // 我们的tomcat 响应方式
            OutputStream outputStream = socket.getOutputStream();
            //换行\r\t
            String respHeader = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html;charset = utf-8\r\n\r\n";
            String resp = respHeader + "hi xycTomcat 徐煜程你好";

            System.out.println("===========我们的Tomcat给浏览器回送的数据=========");
            System.out.println(resp);

            outputStream.write(resp.getBytes());//将resp字符串以byte[]数组返回

            outputStream.flush();
            outputStream.close();
            inputStream.close();
            socket.close();

        }
    }
}
