package com.hung.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo") // GET method
	public String demo(){
		return "Hello World!";
	}
}
