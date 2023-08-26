package com.xyc.tomcat.utils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public class WebUtils {
    public static int parseInt(String strNUm,int defaultVal){
        try {
            return Integer.parseInt(strNUm);
        } catch (NumberFormatException e) {
            System.out.println(strNUm + "strNUm 格式错误");
        }
        return defaultVal;
    }
    //判断uri是不是html文件
    public static boolean isHtml(String uri){
        return  uri.endsWith(".html");
    }
    //根据文件名来读取
    public static String readHtml(String fileName){
        String path = com.xyc.utils.WebUtils.class.getResource("/").getPath();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path + fileName));
            String buf ="";
            while ((bufferedReader.readLine()!=null)){
                stringBuilder.append(buf);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }
}
