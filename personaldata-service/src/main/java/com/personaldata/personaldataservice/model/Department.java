package com.personaldata.personaldataservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {
    private String id;
    private String name;
}
