package com.personaldata.personaldataservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class District {
    private String id;
    private String name;
    private Province province;
}
