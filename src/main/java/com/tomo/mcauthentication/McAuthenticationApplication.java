package com.tomo.mcauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class McAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(McAuthenticationApplication.class, args);
	}

}
