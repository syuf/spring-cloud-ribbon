package com.zlpay.ribbon.rabbitmq;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.zlpay.rabbitmq.constant.QueueNameConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PayMsmConsumer {

	@RabbitListener(queues = QueueNameConstant.PAY_MSM)
	public void message(Message message,Channel channel) {
		log.info("短信业务1受到消息:{},{}",message.getMessageProperties().getDeliveryTag(),new String(message.getBody()));
		try {
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RabbitListener(queues = QueueNameConstant.PAY_MSM)
	public void message2(Message message,Channel channel) {
		log.info("短信业务2受到消息:{},{}",message.getMessageProperties().getDeliveryTag(),new String(message.getBody()));
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//			channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
