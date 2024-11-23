package com.Authentication.roleGuard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan
@EntityScan
@EnableAutoConfiguration
@EnableTransactionManagement
public class RoleGuardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleGuardApplication.class, args);
	}

}
