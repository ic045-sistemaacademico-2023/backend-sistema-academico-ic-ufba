package com.ic045.sistemaacademico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class SistemaAcademicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAcademicoApplication.class, args);
	}

}
