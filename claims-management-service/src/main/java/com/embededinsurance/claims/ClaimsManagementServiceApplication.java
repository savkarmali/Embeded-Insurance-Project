package com.embededinsurance.claims;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient(autoRegister = false)
//@EnableFeignClients
public class ClaimsManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimsManagementServiceApplication.class, args);
		log.info("Claims Management Service Started...");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
