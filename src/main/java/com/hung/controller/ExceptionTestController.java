package com.hung.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exception")
public class ExceptionTestController {
	
	@GetMapping("/ex1")
	public String exception1() {
		int result = 5/0;
		return "success";
	}
	
	@GetMapping("/ex2")
	public String exception2() throws Exception {
		throw new Exception("拋出一個例外!");
	}
	/*
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandler(Exception e, Model model) {
		model.addAttribute("msg", e.getMessage());
		return "exception";
	}
	*/
}
