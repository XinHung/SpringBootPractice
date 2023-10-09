package com.hung.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception e){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("msg", e.getMessage());
        modelAndView.setViewName("exception");

        log.error("[錯誤產生] - " + e.getMessage());
        return modelAndView;
    }

    /*@ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception e, Model model){
        model.addAttribute("msg", e.getMessage());
        return "excep";
    }*/
}
