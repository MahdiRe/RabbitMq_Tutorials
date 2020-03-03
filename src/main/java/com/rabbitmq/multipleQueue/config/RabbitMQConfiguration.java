package com.rabbitmq.multipleQueue.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.common.exchange}")
    String commonExchange;

    @Value("${rabbitmq.employee.queue}")
    String employeeQueue;

    @Value("${rabbitmq.vehicle.queue}")
    String vehicleQueue;

    @Value("${rabbitmq.employee.routingkey}")
    private String employeeRK;

    @Value("${rabbitmq.vehicle.routingkey}")
    private String vehicleRK;


    @Bean
    Queue employeeQueue() {
        return new Queue(employeeQueue, false);
    }

    @Bean
    Queue vehicleQueue() {
        return new Queue(vehicleQueue, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(commonExchange);
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
