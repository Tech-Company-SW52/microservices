package com.fastporte.search_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {
    private String id;
    private String name;
}
