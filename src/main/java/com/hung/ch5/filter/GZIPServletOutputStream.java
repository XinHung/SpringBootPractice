package com.hung.ch5.filter;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

public class GZIPServletOutputStream  extends ServletOutputStream {

    private GZIPOutputStream gzipos;
    public GZIPServletOutputStream(ServletOutputStream sos) throws IOException
    {
        // 使用響應输出串流物件 GZIPOutputStream 過濾串流物件
        this.gzipos = new GZIPOutputStream(sos);
    }


    /**
     * Servlet 3.1 規範新增的方法，檢查非阻塞寫入是否成功，這裡返回 true 即可。
     * @return
     */
    @Override
    public boolean isReady() {
        return true;
    }

    /**
     * Servlet 3.1 規範新增的方法，為這個 ServletOutputStream 設定 WriteListener，
     * 從而切換到非阻塞IO。只有從非同步處理或 HTTP 升级處理切換到非阻塞 IO 才有效。
     * 這裡不須實作。
     * @param writeListener
     */
    @Override
    public void setWriteListener(WriteListener writeListener) { }

    @Override
    public void write(int data) throws IOException {
        // 將寫入操作委託給 GZIPOutputStream 物件的 write() 方法，從而實作響應輸出串流的壓縮
        gzipos.write(data);
    }

    public GZIPOutputStream getGZIPOutputStream() {
        return gzipos;
    }
}
