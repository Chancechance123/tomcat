package com.xyc.utils;

import com.xyc.tomcat.XycTomcatV3;
import org.dom4j.Document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
