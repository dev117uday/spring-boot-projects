package com.backend.urlink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrlinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlinkApplication.class, args);
	}

}
