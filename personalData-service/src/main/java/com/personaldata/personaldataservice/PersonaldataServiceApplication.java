package com.personaldata.personaldataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonaldataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonaldataServiceApplication.class, args);
	}

}
