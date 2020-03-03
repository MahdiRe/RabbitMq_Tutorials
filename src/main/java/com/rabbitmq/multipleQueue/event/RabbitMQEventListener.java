package com.rabbitmq.multipleQueue.event;

import com.rabbitmq.multipleQueue.model.Employee;
import com.rabbitmq.multipleQueue.model.Vehicle;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class RabbitMQEventListener {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${rabbitmq.common.exchange}")
    private String exchange;

    @Value(value = "${rabbitmq.employee.queue}")
    private String employeeQueue;

    @Value(value = "${rabbitmq.vehicle.queue}")
    private String vehicleQueue;

    @Value(value = "${rabbitmq.employee.routingkey}")
    private String employeeRK;

    @Value(value = "${rabbitmq.vehicle.routingkey}")
    private String vehicleRK;

//    public RabbitMQEventListener(RabbitTemplate rabbitTemplate, @Value("${rabbitmq.employee.queue}") String queue,
//                                @Value("${rabbitmq.common.exchange}") String exchange,
//                                @Value("${rabbitmq.employee.routingkey}") String employeeRK) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.exchange = exchange;
//        this.employeeRK = employeeRK;
//    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCreateEvent(Employee event) {
        rabbitTemplate.convertAndSend(this.exchange, this.employeeRK, event);
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onCreateEvent(Vehicle event) {
        rabbitTemplate.convertAndSend(this.exchange, this.vehicleRK, event);
    }

}
