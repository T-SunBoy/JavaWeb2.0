package com.at.el.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.EventListener;

/**
 * @author tao
 * @version 1.0
 * 功能描述:
 * @date 2020-07-15 18:56
 */
public class MyListenerContsextServlet implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext对象创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext对象被销毁");

    }
}
