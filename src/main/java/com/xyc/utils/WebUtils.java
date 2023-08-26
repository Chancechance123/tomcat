package com.xyc.utils;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public class WebUtils {
    public static int parseInt(String strNum, int defaultVal) {
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(strNum + "格式不正确");
        }
        return defaultVal;
    }
}
