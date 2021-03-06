package com.zlpay.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zlpay.ribbon.app.HelloService;


@RestController
public class HelloControler {
	
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String orderId){
	    return helloService.hiService(orderId);
	}
	
}
