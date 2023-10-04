package com.hung.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix="com.hung.random")
public class RamdomDemoController {

	private String secret;
	private int intNumber;	
	private long bigNumber;	
	private String uuid;
	@Value("${com.hung.random.number.less.than.ten}")
	private int lessTenNumber;
	@Value("${com.hung.random.number.int.range}")
	private int range;
	
	public String getSecret() {
		return secret;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public int getIntNumber() {
		return intNumber;
	}
	public void setIntNumber(int intNumber) {
		this.intNumber = intNumber;
	}
	public long getBigNumber() {
		return bigNumber;
	}
	public void setBigNumber(long bigNumber) {
		this.bigNumber = bigNumber;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getLessTenNumber() {
		return lessTenNumber;
	}
	public void setLessTenNumber(int lessTenNumber) {
		this.lessTenNumber = lessTenNumber;
	}
	public int getRang() {
		return range;
	}
	public void setRang(int range) {
		this.range = range;
	}
	
	@GetMapping("randomDemo")
	public String randomDemo() {
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>");
		sb.append("Secret:").append(secret).append("<br>");
		sb.append("Number:").append(intNumber).append("<br>");
		sb.append("BigNumber:").append(bigNumber).append("<br>");
		sb.append("uUid:").append(uuid).append("<br>");
		sb.append("LessThanTen:").append(lessTenNumber).append("<br>");
		sb.append("Range:").append(range).append("</h1>");
		return sb.toString();
	}
}
