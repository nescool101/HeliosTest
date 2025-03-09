package com.co.helios.api.gaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DemoGamingApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoGamingApplication.class, args);
	}
}
