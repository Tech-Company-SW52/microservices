package com.personalData.PersonalData;

import com.personalData.PersonalData.PersonalDataService.initializer.DataInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class PersonalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDataApplication.class, args);
	}

	@Bean
	public DataInitializer dataInitializer() {
		return new DataInitializer();
	}
}
