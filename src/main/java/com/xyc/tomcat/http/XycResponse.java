package com.xyc.tomcat.http;

import java.io.OutputStream;
import java.util.HashMap;

/**
 * @author XYC
 * @version 1.0
 * @DAte 响应 Http 返回数据
 */
public class XycResponse {
    public static OutputStream outputStream = null;

    public static final String respHeader = "HTTP/1.1 200 OK\r\n" +
            "Content-Type: text/html;charset = utf-8\r\n\r\n";

    public XycResponse(OutputStream outputStream) {
        XycResponse.outputStream = outputStream;
    }

    public static OutputStream getOutputStream() {
        return outputStream;
    }
}
