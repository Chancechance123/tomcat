package com.xyc.tomcat.handler;

import com.xyc.tomcat.XycTomcatV3;
import com.xyc.tomcat.http.XycRequest;
import com.xyc.tomcat.http.XycResponse;
import com.xyc.tomcat.servlet.XycCalServlet;
import com.xyc.tomcat.servlet.XycHttpServlet;
import com.xyc.tomcat.utils.WebUtils;

import javax.servlet.Servlet;
import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author XYC
 * @version 1.0
 * @DAte XycRequestHandler是一个线程对象, 处理 http 请求
 */
public class XycRequestHandler implements Runnable {

    // 定义一个socket对象
    private Socket socket = null;

    public XycRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 对浏览器/客户端进行 io交互
        try {
            // InputStream inputStream = socket.getInputStream();
            // BufferedReader bufferedReader =
            //         new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            // System.out.println("======== xycTomcatV2 接收的数据===========");
            // String mes = null;
            // while ((mes = bufferedReader.readLine()) != null) {
            //     if (mes.length() ==0){
            //         break;
            //     }
            //     System.out.println(mes);
            // }

            XycRequest xycRequest = new XycRequest(socket.getInputStream());
            // String num1 = xycRequest.getParameter("num1");
            // String num2 = xycRequest.getParameter("num2");
            // System.out.println("num1 =" + num1);
            // System.out.println("num2 =" + num2);
            // System.out.println("xycRequest" + xycRequest);

            XycResponse xycResponse = new XycResponse(socket.getOutputStream());
            String uri = xycRequest.getUri();

            if (WebUtils.isHtml(uri)){
                String content = WebUtils.readHtml(uri.substring(1));
                content = XycResponse.respHeader + content;
                OutputStream outputStream = xycResponse.getOutputStream();
                outputStream.write(content.getBytes());
                outputStream.flush();
                outputStream.close();
            }

            String servletName = XycTomcatV3.servletUrlMapping.get(uri);
                if (servletName==null){
                    servletName ="";
                }
            XycHttpServlet xycHttpServlet = XycTomcatV3.servletMapping.get(servletName);
            if (xycHttpServlet!=null){
                xycHttpServlet.service(xycRequest,xycResponse);

            }else {
                String resp = XycResponse.respHeader + "<h1>404 No Fund<h1>";
                OutputStream outputStream = XycResponse.getOutputStream();
                outputStream.write(resp.getBytes());
                outputStream.flush();
                outputStream.close();
            }

            // XycCalServlet xycCalServlet = new XycCalServlet();
            // xycCalServlet.doGet(xycRequest,xycResponse);
            // String resp = XycResponse.respHeader + "<h1> xycResponse返回的信息 hi xycResponse <h1>";
            // OutputStream outputStream = xycResponse.getOutputStream();
            // outputStream.write(resp.getBytes());
            //
            // outputStream.flush();
            // outputStream.close();
            // String respHeader = "HTTP/1.1 200 OK\r\n" +
            //         "Content-Type: text/html;charset = utf-8\r\n\r\n";
            // String resp = respHeader + "hi xycTomcat 徐煜程你好";
            //
            // System.out.println("========xycTomcatV2 返回的数据===========");
            // OutputStream outputStream = socket.getOutputStream();
            // outputStream.write(resp.getBytes());
            //
            // outputStream.flush();
            // outputStream.close();
            // inputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
