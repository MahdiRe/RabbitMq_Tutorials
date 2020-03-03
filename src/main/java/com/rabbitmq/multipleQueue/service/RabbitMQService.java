package com.rabbitmq.multipleQueue.service;

import com.rabbitmq.multipleQueue.model.Employee;
import com.rabbitmq.multipleQueue.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishToEmployeeQueue(Employee employee){

        publisher.publishEvent(employee);

    }

    public void publishTovehicleQueue(Vehicle vehicle){

        publisher.publishEvent(vehicle);

    }

}
