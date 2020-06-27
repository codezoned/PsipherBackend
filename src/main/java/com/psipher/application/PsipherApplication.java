package com.psipher.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class PsipherApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PsipherApplication.class, args);
	}

}
