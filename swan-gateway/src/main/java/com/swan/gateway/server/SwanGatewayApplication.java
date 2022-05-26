package com.swan.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SwanGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SwanGatewayApplication.class, args);
	}

}
