package com.hiring.hiringservice;

import com.hiring.hiringservice.initializer.NotificationInitializer;
import com.hiring.hiringservice.initializer.StatusContractInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class HiringServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HiringServiceApplication.class, args);
	}

	@Bean
	public StatusContractInitializer intitializer1() {
		return new StatusContractInitializer();
	}

	@Bean
	public NotificationInitializer intitializer2() {
		return new NotificationInitializer();
	}

}
