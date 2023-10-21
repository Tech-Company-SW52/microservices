package com.fastporte.search_service.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Long id;

    private Carrier carrier;

    private String photo;

    private String type;

    private Long quantity;

}
