package com.rabbitmqguide.springbootrabbitmq.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Integer id;
    private String firstName;
    private String lastName;
}
