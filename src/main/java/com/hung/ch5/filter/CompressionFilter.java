package com.hung.ch5.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

//@WebFilter(urlPatterns = "/*", filterName = "compressionFilter")
@WebFilter(urlPatterns = "/compressionFilter/*", filterName = "compressionFilter")
public class CompressionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        String acceptEncodings = httpReq.getHeader("Accept-Encoding");
        if (acceptEncodings != null && acceptEncodings.indexOf("gzip") > -1) {

            CompressionResponseWrapper respWrapper = new CompressionResponseWrapper(
                    httpResp);

            // 設定 Content-Encoding 實體標頭，告訴瀏覽器實體正文採用了 gzip 壓縮編碼
            respWrapper.setHeader("Content-Encoding", "gzip");
            filterChain.doFilter(httpReq, respWrapper);

            // 得到 GZIPOutputStream 輸出串流物件
            GZIPOutputStream gzipos = respWrapper.getGZIPOutputStream();
            // 呼叫 GZIPOutputStream 輸出串流物件的 finish() 方法完成將壓縮資料寫入響應輸出串流的操作，
            // 無須關閉輸出流
            gzipos.finish();
        } else {
            filterChain.doFilter(httpReq, httpResp);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
