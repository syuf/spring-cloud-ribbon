package com.zlpay.ribbon.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues="hello")
public class Consumer {

	@RabbitHandler
    public void process(String hello){
        System.out.print("reciver:"+hello);
    }
}
