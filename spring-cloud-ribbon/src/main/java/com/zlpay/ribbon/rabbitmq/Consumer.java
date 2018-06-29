package com.zlpay.ribbon.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zlpay.common.constant.QueueConstants;
import com.zlpay.ribbon.app.HelloService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RabbitListener(queues= QueueConstants.EMAIL)
public class Consumer {
	
	@Autowired
	private HelloService helloService;

	@RabbitHandler
    public void process(String content){
        log.info("reciver:{}",content);
        helloService.hiService("hello 啊");
    }
}
