package com.rabbitmqguide.springbootrabbitmq.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;
    @Value("${rabbitmq.queue.name}")
    String queueName;

    @Value("${rabbitmq.exchange.name}")
    String exchangeName;

    @Value("${rabbitmq.routing.key.name}")
    String routingKeyName;

    @Value("${rabbitmq.routing.json.key}")
    String routingJsonKey;
    @Bean
    public Queue queue(){
        return new Queue(queueName);
    }

    //Spring Bean for queue
    @Bean
    public Queue jsonQueue(){
        return new Queue(jsonQueue);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchangeName);
    }


    //BINDING BETWEEN QUEUE AND EXCHANGE USING ROUTING KEY
    @Bean
     public Binding binding(){
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKeyName);
    }

    @Bean
    public Binding bindingJson(){
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
