package com.ic045.sistemaacademico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SistemaAcademicoApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/sistemaacademico");
		SpringApplication.run(SistemaAcademicoApplication.class, args);
	}

}
