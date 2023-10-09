package com.hung.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
	
	@RequestMapping("/pathVar1/{name}/{id}")
    public String handleUrl(@PathVariable("name") String v_name, @PathVariable Integer id){
        String result = "<h1>";
        
        result += "name: " + v_name + "<br>";
        result += "id: " + id + "<br>";
        result += "</h1>";
        
        return result;
    }
	
	@RequestMapping("/pathVar2/{name}/{id}")
    public String handleUrl(@PathVariable Map pathMap){
		String result = "<h1>";
        
        result += "name: " + pathMap.get("name") + "<br>";
        result += "id: " + pathMap.get("id") + "<br>";
        result += "</h1>";
        
        return result;
    }
	
	@RequestMapping("/header")
    public String handleHeader(@RequestHeader("User-Agent") String userAgent){
        System.out.println(userAgent);
        return "userAgent: " + userAgent;
    }

    @RequestMapping("/allHeaders")
    public String handleAllHeaders(@RequestHeader Map headersMap){
        System.out.println(headersMap);
        return "allHeaders: " + headersMap.toString();
    }
    
	/*@RequestMapping("/json")
	public String handelJson(@RequestBody User user) {
		return "ok!";
	}*/
}
