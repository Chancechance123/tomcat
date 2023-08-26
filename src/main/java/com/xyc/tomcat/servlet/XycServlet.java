package com.xyc.tomcat.servlet;

import com.xyc.tomcat.http.XycRequest;
import com.xyc.tomcat.http.XycResponse;

import java.io.IOException;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public interface XycServlet {
    void init() throws Exception;



    void service(XycRequest request, XycResponse response) throws  IOException;



    void destroy();
}
