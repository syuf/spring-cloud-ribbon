package com.zlpay.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zlpay.ribbon.app.HelloService;

@RestController
public class HelloControler {
	
//	@Value("${url}")
//    private String url;
	
	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String orderId){
	    return helloService.hiService(orderId);
	}
	
//	@RequestMapping(value = "/readUserName",method = RequestMethod.GET)
//    public String read(){
//        return url;
//    }
}
