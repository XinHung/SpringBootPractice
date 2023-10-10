package com.hung.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class FileController {
    
    @GetMapping("/upload")
    public String doUpload(Model model, HttpSession session){
    	// 將上傳檔案目錄下的所有文件名稱列出來，保存到模型物件中
        String uploadPath = "D:" + File.separator + "SpringBootUpload";
        File dir = new File(uploadPath);
        model.addAttribute("files", dir.list());
        
        if(session.getAttribute("uploadMsg") != null) {
        	model.addAttribute("uploadMsg", session.getAttribute("uploadMsg"));
        	session.removeAttribute("uploadMsg");
        }
        return "upload";
    }

    @PostMapping("/upload")
    //@ResponseBody
    public String upload(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        // 得到所有文件的列表
        List<MultipartFile> files =
                ((MultipartHttpServletRequest) request).getFiles("file");
        String uploadPath = "D:" + File.separator + "SpringBootUpload";
        File dir = new File(uploadPath);
        // 如果目錄不存在，建立之
        if(!dir.exists()) {
            dir.mkdirs();
        }
        for(MultipartFile f: files) {
            if(f.isEmpty()) {
                continue;
            }
            File target = new File(uploadPath + File.separator + f.getOriginalFilename());
            try {
                f.transferTo(target);
            } catch (IllegalStateException | IOException e) {
            	e.printStackTrace();
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
        
        session.setAttribute("uploadMsg", "上傳成功!");
        response.sendRedirect("upload");
        return "upload";
        //return "上傳成功";
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam String fileName) throws IOException {
        String dir = "D:" + File.separator + "SpringBootUpload";
        String fileFullPath = dir + File.separator + fileName;
        File file = new File(fileFullPath);
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.contentLength(file.length());
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        fileName = new String(fileName.getBytes(StandardCharsets.UTF_8),
                StandardCharsets.ISO_8859_1);
        builder.header("Content-Disposition", "attachment; filename=" + fileName);
        return builder.body(FileUtils.readFileToByteArray(file));
    }
}
