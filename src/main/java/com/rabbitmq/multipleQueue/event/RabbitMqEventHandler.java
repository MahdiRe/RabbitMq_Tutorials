package com.rabbitmq.multipleQueue.event;

import com.rabbitmq.multipleQueue.model.Employee;
import com.rabbitmq.multipleQueue.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventHandler {

    Logger log = LoggerFactory.getLogger(RabbitMqEventHandler.class);

    @RabbitListener(queues = { "${rabbitmq.employee.queue}" })
    public void handle(Employee event) throws Exception {
//        try {
            System.out.println("Event Received: " + event);
            throw new AmqpRejectAndDontRequeueException("Exception throwing test started...");
//            throw new Exception("Exception throwing test started...");
//        }catch (Exception e){
//            log.error("Error at Employee handle: " + e);
//        }
    }

    @RabbitListener(queues = { "${rabbitmq.vehicle.queue}" })
    public void handle(Vehicle event) throws Exception {
//        try {
            System.out.println("Event Received: " + event);
            throw new AmqpRejectAndDontRequeueException ("Exception throwing test started...");
//            throw new Exception("Exception throwing test started...");
//        }catch (Exception e){
//            log.error("Error at Vehicle handle: " + e);
//        }
    }
}
