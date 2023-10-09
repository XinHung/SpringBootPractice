package com.hung.ch5.filter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

public class CompressionResponseWrapper extends HttpServletResponseWrapper {

    private final GZIPServletOutputStream gzipsos;
    private final PrintWriter pw;

    public CompressionResponseWrapper(HttpServletResponse response)
            throws IOException {
        super(response);
        // 用響應輸出串流建立 GZIPServletOutputStream 物件
        gzipsos = new GZIPServletOutputStream(response.getOutputStream());
        // 用 GZIPServletOutputStream 物件作為參數，建構 PrintWriter 物件
        pw = new PrintWriter(gzipsos);
    }

    /**
     * Override setContentLength()方法，以避免 Content-Length 實體標頭長度
     * 和壓縮後的實體正文長度不匹配
     */
    @Override
    public void setContentLength(int len) {}

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return gzipsos;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return pw;
    }

    /**
     * 篩檢程式呼叫這個方法来得到 GZIPOutputStream 物件，以便完成將壓縮資料寫入输出串流的操作
     */
    public GZIPOutputStream getGZIPOutputStream() {
        return gzipsos.getGZIPOutputStream();
    }

}
