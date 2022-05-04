package com.tomo.mcauthentication;

import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableConfigurationProperties(AppProperties.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class McAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(McAuthenticationApplication.class, args);
	}

}
