package com.hung.ch6.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判斷是否已經登入
        Object user = request.getSession().getAttribute("user");
        if(user == null) {
            // 沒有登入，把原本的 URI 存起來
            String requestUri = request.getRequestURI();

            String strQuery = request.getQueryString();
            if (null != strQuery) {
                requestUri = requestUri + "?" + strQuery;
            }
            request.setAttribute("originUri", requestUri);

            request.setAttribute("error", "您沒有訪問權限，請先登入！");
            request.getRequestDispatcher("/user/login").forward(request, response);
            return false;
        } else {
            // 已登入，回傳 true
            return true;
        }
    }
}
