package com.personalData.PersonalData.PersonalDataService.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Esta clase de configuración define un bean para ModelMapper.
 * ModelMapper es una biblioteca que se utiliza para mapear
 * automáticamente una clase de objeto a otra.
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}