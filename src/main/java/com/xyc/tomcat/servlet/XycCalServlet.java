package com.xyc.tomcat.servlet;

import com.xyc.tomcat.http.XycRequest;
import com.xyc.tomcat.http.XycResponse;
import com.xyc.tomcat.utils.WebUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public class XycCalServlet extends XycHttpServlet {
    @Override
    public void doGet(XycRequest request, XycResponse response) {
        int num1 = WebUtils.parseInt(request.getParameter("num1"), 0);
        int num2 = WebUtils.parseInt(request.getParameter("num2"), 0);
        int sum = num1 + num2;

        // 返回数据
        OutputStream outputStream = response.getOutputStream();
        String resp = XycResponse.respHeader + "<h1> " + num1 + "+ " + num2 + " = " + sum + " XycTomcatV3 反射创建</h1>";

        try {
            outputStream.write(resp.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(XycRequest request, XycResponse response) {
        this.doGet(request, response);
    }

    @Override
    public void init() throws Exception {

    }

    @Override
    public void destroy() {

    }
}
