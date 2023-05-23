package com.rabbitmqguide.springbootrabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQProducer {


    @Value("${rabbitmq.exchange.name}")
    String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    String routingKeyName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message){
        log.info("Message sent -> {}",message);
        rabbitTemplate.convertAndSend(exchangeName,routingKeyName,message);
    }

}
