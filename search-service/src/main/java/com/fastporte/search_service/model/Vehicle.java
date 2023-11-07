package com.fastporte.search_service.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private User carrier;
    private String photo;
    private String type;
    private Long quantity;
}
