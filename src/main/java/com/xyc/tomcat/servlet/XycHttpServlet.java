package com.xyc.tomcat.servlet;

import com.xyc.tomcat.http.XycRequest;
import com.xyc.tomcat.http.XycResponse;

import java.io.IOException;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public abstract class XycHttpServlet implements XycServlet{
    @Override
    public void service(XycRequest request, XycResponse response) throws IOException {
        if ("Get".equalsIgnoreCase(request.getMethod())){
                this.doGet(request,response);
        }else if ("Post".equalsIgnoreCase(request.getMethod())){
            this.doPost(request,response);
        }
    }
    public abstract void doGet(XycRequest request,XycResponse response);
    public abstract void doPost(XycRequest request,XycResponse response);
}
