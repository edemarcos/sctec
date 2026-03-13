package com.empemsc.sctec;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "SCTEC API", version = "1.0", description = "API para o sistema SCTEC"))
public class SctecApplication {

	public static void main(String[] args) {
		SpringApplication.run(SctecApplication.class, args);
	}

}
