package com.fipe.fipeapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fipe.fipeapplication.principais.Principal;
import com.fipe.fipeapplication.service.FipeClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class FipeapplicationApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(FipeapplicationApplication.class, args);
	}

	public void run(String... args) throws JsonProcessingException {

		Principal principal = new Principal();
		principal.mostraMenu();

	}
}
