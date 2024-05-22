package com.RabbitMQConsumer.RabbitMQConsumer.models;

import lombok.Data;

@Data
public class Address {
    private String road;
    private String neighborhood;
    private String city;
    private String postal_code;
    private Integer number;
}
