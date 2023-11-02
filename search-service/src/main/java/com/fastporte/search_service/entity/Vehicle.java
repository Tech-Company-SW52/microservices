package com.fastporte.search_service.entity;


import com.fastporte.search_service.model.Carrier;
import lombok.*;

@Data
@Builder
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
