package com.codingjump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SolaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolaceApplication.class, args);
	}
}
