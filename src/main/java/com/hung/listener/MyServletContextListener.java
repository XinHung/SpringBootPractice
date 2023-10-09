package com.hung.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        //System.out.println("即將超出 web 應用程式範圍");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        //System.out.println("即將進入 web 應用程式範圍");
    }
}
