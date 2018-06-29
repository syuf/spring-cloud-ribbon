package com.zlpay.ribbon.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zlpay.common.constant.QueueConstants;

@Configuration
public class RabbitmqConfiguration {
	
	@Bean
    public Queue rabbitMqQueue(){
    	return new Queue(QueueConstants.EMAIL);
    }
}
