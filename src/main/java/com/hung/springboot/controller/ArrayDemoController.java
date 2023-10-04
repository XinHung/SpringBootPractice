package com.hung.springboot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArrayDemoController {

	@Value("${arrExample.empId}")
	private List<String> idList;
	@Value("#{${map.pet.sound}}")
	private Map<String, String> petMap;
	
	@GetMapping("list")
	public String listDemo() {
		return idList.toString();
	}
	
	@GetMapping("map")
	public String mapDemo() {
		return petMap.toString();
	}
}
