package com.rabbitmq.multipleQueue.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.common.exchange}")
    String commonExchange;

    @Value("${rabbitmq.deadLetter.exchange}")
    String deadLetterExchange;

    @Value("${rabbitmq.employee.queue}")
    String employeeQueue;

    @Value("${rabbitmq.vehicle.queue}")
    String vehicleQueue;

    @Value("${rabbitmq.deadLetter.queue}")
    String deadLetterQueue;

    @Value("${rabbitmq.employee.routingkey}")
    private String employeeRK;

    @Value("${rabbitmq.vehicle.routingkey}")
    private String vehicleRK;

    @Value("${rabbitmq.deadLetter.routingkey}")
    private String deadLetterRK;


    @Bean
    Queue dlq() {
        return QueueBuilder.durable(deadLetterQueue).build();
    }

    @Bean
    Queue employeeQueue() {
//        return new Queue(employeeQueue, false);
//        return QueueBuilder.nonDurable(employeeQueue).withArgument("x-dead-letter-exchange", deadLetterExchange)
//                .withArgument("x-dead-letter-routing-key", deadLetterRK).build();
        return QueueBuilder.nonDurable(employeeQueue).withArgument("x-dead-letter-exchange", deadLetterExchange).build();
    }

    @Bean
    Queue vehicleQueue() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-dead-letter-exchange", deadLetterExchange);
//        args.put("x-dead-letter-routing-key", deadLetterRK);
//        return new Queue(vehicleQueue, false, false, false, args);

//        return QueueBuilder.nonDurable(vehicleQueue).withArgument("x-dead-letter-exchange", deadLetterExchange)
//                .withArgument("x-dead-letter-routing-key", deadLetterRK).build();
        return QueueBuilder.nonDurable(vehicleQueue).withArgument("x-dead-letter-exchange", deadLetterExchange).build();
    }

    @Bean  // done
    DirectExchange exchange() {
        return new DirectExchange(commonExchange);
    }

    @Bean // done
    FanoutExchange deadLetterExchange() {
        return new FanoutExchange(deadLetterExchange);
    }

    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange());
    }

    @Bean
    Binding employeeBinding(Queue employeeQueue, DirectExchange exchange) {
        return BindingBuilder.bind(employeeQueue).to(exchange).with(employeeRK);
    }

    @Bean
    Binding vehicleBinding(Queue vehicleQueue, DirectExchange exchange) {
        return BindingBuilder.bind(vehicleQueue).to(exchange).with(vehicleRK);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
