package com.xyc.tomcat;

import com.xyc.tomcat.handler.XycRequestHandler;
import com.xyc.tomcat.servlet.XycHttpServlet;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XYC
 * @version 1.0
 * @DAte
 */
public class XycTomcatV3 {
    // 1.存放容器     servletMapping
    // key                   value
    // ServletName           对应实例
    public static final ConcurrentHashMap<String, XycHttpServlet>
            servletMapping = new ConcurrentHashMap<>();


    // 2.存放容器       servletURLMapping
    // key                  value
    // URL-pattern          ServletName
    public static final ConcurrentHashMap<String, String>
            servletUrlMapping = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        XycTomcatV3 xycTomcatV3 = new XycTomcatV3();
        xycTomcatV3.init();
        // 启动XycTomcat
        xycTomcatV3.run();
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("============XycTomcatV3 在8080端口监听==========");
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                XycRequestHandler xycRequestHandler = new XycRequestHandler(socket);
                new Thread(xycRequestHandler).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 对两个容器进行初始化
    public void init() {
        String path = XycTomcatV3.class.getResource("/").getPath();
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new File(path + "web.xml"));
            System.out.println(document);
            // 得到所有根节点
            Element rootElement = document.getRootElement();
            // 得到所有根节点下面的子元素
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                if ("servlet".equalsIgnoreCase(element.getName())) {
                    System.out.println("发现了 servlet");
                    Element servletName = element.element("servlet-name");
                    Element servletClass = element.element("servlet-class");

                    servletMapping.put(servletName.getText(),
                            (XycHttpServlet) Class.forName(servletClass.getText().trim()).newInstance());
                } else if ("servlet-mapping".equalsIgnoreCase(element.getName())) {
                    System.out.println("发现了 servlet-mapping");
                    Element servletName = element.element("servlet-name");
                    Element urlPattern = element.element("url-pattern");
                    servletUrlMapping.put(urlPattern.getText(), servletName.getText());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("servletMapping" + servletMapping);
        System.out.println("servletUrlMapping" + servletUrlMapping);
    }

}
