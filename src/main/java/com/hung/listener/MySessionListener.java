package com.hung.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MySessionListener implements HttpSessionAttributeListener {
	@Override  
    public void attributeAdded(HttpSessionBindingEvent event) {  
        String attributeName = event.getName();  
        Object attributeValue = event.getValue();  
        System.out.println("HttpSessionAttributeListener Attribute added : " + attributeName + " : "  
                + attributeValue);  
    }  
  
    @Override  
    public void attributeRemoved(HttpSessionBindingEvent event) {  
        String attributeName = event.getName();  
        Object attributeValue = event.getValue();  
        System.out.println("HttpSessionAttributeListener Attribute removed : " + attributeName + " : "  
                + attributeValue);  
    }  
  
    @Override  
    public void attributeReplaced(HttpSessionBindingEvent event) {  
        String attributeName = event.getName();  
        Object attributeValue = event.getValue();  
        System.out.println("Attribute replaced : " + attributeName + " : "  
                + attributeValue);  
    }
}
