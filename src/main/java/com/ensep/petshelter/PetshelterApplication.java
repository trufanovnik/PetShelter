package com.ensep.petshelter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PetshelterApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetshelterApplication.class, args);
	}

}
