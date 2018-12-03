package com.cg.project.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= {"com.cg.project"})
@EntityScan(basePackages="com.cg.project.beans")
@EnableJpaRepositories(basePackages="com.cg.project.daoservices")
@EnableWebMvc
public class CapBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapBookApplication.class, args);
	}
}
