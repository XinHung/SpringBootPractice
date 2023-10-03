package com.hung.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/demo") // GET method
	public String demo(){
		return "Hello World!";
	}
	
	// @PathVariable 取得路徑變數
	@GetMapping("/home/{id}")
	public String pathVariable(@PathVariable("id") String id) {
	    return "<h1>ID: " + id + "</h1>";
	}
	
	// @RequestParam 取得 GET method 變數
	@GetMapping("/home")
    public String requestParamGet(@RequestParam("id") String id){
        return "<h1>ID: " + id + "</h1>" + "</h1>";
    }
    
	// @RequestParam 取得 POST method 變數
    @PostMapping("/home")
    public String requestParamPost(@RequestParam("id") String id){
        return "<h1>ID: " + id + "</h1>";
    }
}
