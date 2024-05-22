package com.RabbitMQConsumer.RabbitMQConsumer.models;

import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String name;
    private Integer age;
    private String password;
    private String email;
    private Address address;
}
