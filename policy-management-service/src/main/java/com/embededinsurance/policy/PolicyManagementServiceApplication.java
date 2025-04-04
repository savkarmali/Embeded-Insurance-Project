package com.embededinsurance.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
public class PolicyManagementServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PolicyManagementServiceApplication.class, args);
		log.info("Policy Management Service Started ...");
		
	}

}
