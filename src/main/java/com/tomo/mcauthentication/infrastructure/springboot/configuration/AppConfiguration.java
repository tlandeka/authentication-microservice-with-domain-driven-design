package com.tomo.mcauthentication.infrastructure.springboot.configuration;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRespository;
import com.tomo.mcauthentication.infrastructure.McAuthenticationModuleExecutor;
import com.tomo.mcauthentication.infrastructure.persistence.UserJpaRepository;
import com.tomo.mcauthentication.infrastructure.persistence.UserRegistrationJpaRepository;
import com.tomo.mcauthentication.infrastructure.persistence.UserRegistrationJpaRepositoryAdapter;
import com.tomo.mcauthentication.infrastructure.persistence.UserRespositoryJpaAdapter;
import com.tomo.mcauthentication.infrastructure.processing.builder.CommandHandlerPipelineBuilder;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Collectors;

@Configuration
@EnableJpaRepositories
public class AppConfiguration {

    @Autowired
    UserRegistrationJpaRepository userRegistrationJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Bean
    McAuthenticationModule authenticationModule(CommandHandlerPipelineBuilder commandHandlerPipelineBuilder) {
        return new McAuthenticationModuleExecutor(commandHandlerPipelineBuilder);
    }

    @Bean
    CommandHandlerPipelineBuilder commandHandlerPipelineBuilder(ObjectProvider<CommandHandler> commandHandlers) {
        return new CommandHandlerPipelineBuilder(commandHandlers.stream().collect(Collectors.toList()));
    }

    @Bean
    UserRegistrationRepository userRegistrationRepository(){
        return new UserRegistrationJpaRepositoryAdapter(userRegistrationJpaRepository);
    }

    @Bean
    UserRespository userRespository() {
        return new UserRespositoryJpaAdapter(userJpaRepository);
    }

}
