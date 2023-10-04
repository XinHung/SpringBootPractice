package com.hung.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hung.springboot.utils.ConfigBean;

@RestController
public class BeanDemoController {

	@Autowired
	ConfigBean configBean;
	
	@GetMapping("beanDemo")
	public String beanDemo() {
		String result = "<h1>";
		
		result += "Food: " + configBean.getFood() + "<br>";
		result += "Drink: " + configBean.getDrink() + "<br>";
		result += "Sport: " + configBean.getSport() + "<br>";
		
		result += "</h1>";
		
		return result;
	}

}
