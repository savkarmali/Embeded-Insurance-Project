package com.embededinsurance.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PolicyManagementServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PolicyManagementServiceApplication.class, args);
		log.info("Policy Management Service Started");
		
	}

}
