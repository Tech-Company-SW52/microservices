package com.personalData.PersonalData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonalDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalDataApplication.class, args);
	}
}
