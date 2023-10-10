package com.hung.controller;

import com.hung.result.BaseResult;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ControllerAdvice
public class GlobalErrorController implements ErrorController {
    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public ResponseEntity<BaseResult> error(HttpServletResponse response){
        int code = response.getStatus();
        BaseResult result = null;
        switch (code){
            case 401:
                result = new BaseResult(
                        401, "使用者未登入");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            case 403:
                result = new BaseResult(
                        403, "沒有存取權限");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
            case 404:
                result = new BaseResult(
                        404, "此頁面不存在");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
            case 405:
                result = new BaseResult(
                        405, "無法使用");
                return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(result);
            case 500:
                result = new BaseResult(
                        500, "伺服器端錯誤");
            default:
                result = new BaseResult(
                        500, "未知錯誤");
                return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
    /*
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResult> exception(Exception e) {
        BaseResult result = new BaseResult(500, e.getMessage());
        return ResponseEntity.status(
                HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
    */
}