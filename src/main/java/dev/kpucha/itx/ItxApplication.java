package dev.kpucha.itx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan
@OpenAPIDefinition
public class ItxApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItxApplication.class, args);
	}

}
