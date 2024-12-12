package com.example.mylibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MylibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MylibraryApplication.class, args);
	}

}
