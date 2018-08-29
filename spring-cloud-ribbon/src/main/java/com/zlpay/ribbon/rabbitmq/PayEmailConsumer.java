package com.zlpay.ribbon.rabbitmq;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zlpay.rabbitmq.constant.QueueNameConstant;
import com.zlpay.redis.client.RedisClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PayEmailConsumer {
	
	@Autowired
	private RedisClient redisClient;

	@RabbitListener(queues = QueueNameConstant.PAY_EMAIL)
	public void message(Message message,Channel channel) {
		log.info("邮件业务受到消息:{},{}",message.getMessageProperties().getDeliveryTag(),new String(message.getBody()));
		int number = 1;
		try {
			log.info("消息message:{}",JSON.toJSONString(message));
			log.info("消息channel:{}",JSON.toJSONString(channel));
			String msg = redisClient.get(new String(message.getBody()));
			if(StringUtils.isEmpty(msg)) {
				log.info("消息ID:{}已经被消费",message.getMessageProperties().getMessageId());
				number = 2;
				return;
			}
			//业务场景
			number = 3;
			//ack
		} catch (Exception e) {
			log.error("消费消息异常!!",e);
		} finally {
			try {
				if(number == 1) {
					channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
				} else if(number == 2) {
					channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
				} if(number == 3) {
					channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
					redisClient.remove(message.getMessageProperties().getMessageId());
				}
			} catch (IOException e) {
				log.error("确认消费消息异常!!",e);
			}
			
		}
	}
}
