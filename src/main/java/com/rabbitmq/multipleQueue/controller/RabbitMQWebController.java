package com.rabbitmq.multipleQueue.controller;

import com.rabbitmq.multipleQueue.model.Employee;
import com.rabbitmq.multipleQueue.model.Vehicle;
import com.rabbitmq.multipleQueue.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rabbitmq")
public class RabbitMQWebController {

    @Autowired
    RabbitMQService rabbitMQService;

    @PostMapping(value = "/employee")
    public String publishEmployeeQueue(@RequestBody Employee employee){

        rabbitMQService.publishToEmployeeQueue(employee);
        return "Published to Employee!";

    }

    @PostMapping(value = "/vehicle")
    public String publishVehicleQueue(@RequestBody Vehicle vehicle){

        rabbitMQService.publishTovehicleQueue(vehicle);
        return "Published to Vehicle";

    }

}
