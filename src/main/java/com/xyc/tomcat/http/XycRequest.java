package com.xyc.tomcat.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * @author XYC
 * @version 1.0
 * @DAte 请求 Get请求
 */
public class XycRequest {
    private String method;
    private String uri;
    private HashMap<String, String> parametersMapping = new HashMap<>();
    private InputStream inputStream = null;
    // inoutStream 与 对应的Http请求的 socket 关联
    public XycRequest(InputStream inputStream) {
       this.inputStream = inputStream;
        //完成封装 初始化
        encapHttp();
    }
    public void encapHttp(){
        System.out.println("XycRequest 中的 init() 被调用");
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String requestLine = bufferedReader.readLine();
            String[] requestLineArr = requestLine.split(" ");

            // 获得方法
            method = requestLineArr[0];
            // 获得uri
            int index = requestLineArr[1].indexOf("?");
            if (index == -1) {
                uri = requestLineArr[1];
            } else {
                uri = requestLineArr[1].substring(0, index);
                String parameters = requestLineArr[1].substring(index + 1);
                String[] parametersPair = parameters.split("&");
                if (null != parametersPair && !"".equals(parametersPair)) {
                    for (String parameterPair : parametersPair) {
                        String[] parameterVal = parameterPair.split("=");
                        if (parameterVal.length == 2) {
                            parametersMapping.put(parameterVal[0],parameterVal[1]);
                        }
                    }
                }
            }
            // inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String getParameter(String name) {
        if (parametersMapping.containsKey(name)){
            return parametersMapping.get(name);
        }else{
            return "";
        }
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "XycRequest{" +
                "method='" + method + '\'' +
                ", uri='" + uri + '\'' +
                ", parametersMapping=" + parametersMapping +
                '}';
    }
}
