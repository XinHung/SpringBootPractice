package com.hung.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@Value("${author}")
	private String author;
	@Value("${learningDate}")
	private String learningDate;
	@Value("${learningTarget}")
	private String learningTarget;
	@Value("${com.hung.adminAcc}")
	private String adminAcc;
	@Value("${com.hung.adminPwd}")
	private String adminPwd;
	@Value("${com.hung.adminEmpId}")
	private String adminEmpId;
	
	@GetMapping("")
	public String appProp() {
		String result = "<h1>";
		
		result += "Author: " + author + "<br>";
		result += "LearningDate: " + learningDate + "<br>";
		result += "LearningTarget: " + learningTarget + "<br><br>";
		
		result += "AdminAcc: " + adminAcc + "<br>";
		result += "AdminPwd: " + adminPwd + "<br>";
		result += "AdminEmpId: " + adminEmpId + "<br>";
		
		result += "</h1>";
		
		return result;
	}

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
