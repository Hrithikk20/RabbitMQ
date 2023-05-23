package com.rabbitmqguide.springbootrabbitmq.consumer;


import com.rabbitmqguide.springbootrabbitmq.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consumeJson(User user){
        log.info("Received Json Message -> {}",user);
    }

}
