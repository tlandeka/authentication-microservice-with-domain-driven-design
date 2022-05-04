package com.tomo.mcauthentication.infrastructure.springboot.configuration;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.ddd.email.EmailSender;
import com.tomo.mcauthentication.ddd.port.adapter.message.email.MailGunMessageSender;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Service;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.infrastructure.McAuthenticationModuleExecutor;
import com.tomo.mcauthentication.infrastructure.http.oauth2.FacebookOAuth2Authentication;
import com.tomo.mcauthentication.infrastructure.http.oauth2.GoogleOAuth2Authentication;
import com.tomo.mcauthentication.infrastructure.persistence.UserJpaRepository;
import com.tomo.mcauthentication.infrastructure.persistence.UserRegistrationJpaRepository;
import com.tomo.mcauthentication.infrastructure.persistence.UserRegistrationJpaRepositoryAdapter;
import com.tomo.mcauthentication.infrastructure.persistence.UserRepositoryJpaAdapter;
import com.tomo.mcauthentication.infrastructure.processing.builder.CommandHandlerPipelineBuilder;
import com.tomo.mcauthentication.infrastructure.processing.builder.QueryHandlerPipelineBuilder;
import com.tomo.mcauthentication.infrastructure.springboot.filter.TokenAuthenticationFilter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

@Configuration
@EnableJpaRepositories
public class AppConfiguration {

    @Autowired
    UserRegistrationJpaRepository userRegistrationJpaRepository;

    @Autowired
    UserJpaRepository userJpaRepository;

    @Autowired
    private Environment env;

    @Autowired
    AppProperties appProperties;

    @Bean
    String recoveryLink() {
        return appProperties.getGui().getRecoveryRoute();
    }

    @Bean
    String confirmationLink() {
        return appProperties.getGui().getConfirmationRoute();
    }

    @Bean
    net.sargue.mailgun.Configuration mailGunConfiguration() {
        MessageProperties.Email.MailGun mailGun = appProperties.getMessage().getEmail().getMailGun();
        net.sargue.mailgun.Configuration configuration = new net.sargue.mailgun.Configuration()
                .domain(mailGun.getDomains())
                .apiUrl(mailGun.getApiUrl())
                .apiKey(mailGun.getApiKey())
                .from(mailGun.getFrom().getName(), mailGun.getFrom().getEmail());

        return configuration;
    }

    @Bean
    EmailSender emailMessageSender(net.sargue.mailgun.Configuration mailGunConfiguration) {
        return new MailGunMessageSender(mailGunConfiguration);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    @Bean
    McAuthenticationModule authenticationModule(CommandHandlerPipelineBuilder commandHandlerPipelineBuilder,
            QueryHandlerPipelineBuilder queryHandlerPipelineBuilder) {
        return new McAuthenticationModuleExecutor(commandHandlerPipelineBuilder, queryHandlerPipelineBuilder);
    }

    @Bean
    CommandHandlerPipelineBuilder commandHandlerPipelineBuilder() {
        return new CommandHandlerPipelineBuilder();
    }

    @Bean
    QueryHandlerPipelineBuilder queryHandlerPipelineBuilder() {
        return new QueryHandlerPipelineBuilder();
    }

    @Bean
    UserRegistrationRepository userRegistrationRepository(){
        return new UserRegistrationJpaRepositoryAdapter(userRegistrationJpaRepository);
    }

    @Bean
    UserRepository userRepository() {
        return new UserRepositoryJpaAdapter(userJpaRepository);
    }

    @Bean
    @Qualifier("facebookClientRegistration")
    ClientRegistration facebookClientRegistration() {
        String clientRootProperty = "spring.security.oauth2.client.registration.facebook";
        String clientId = env.getProperty(clientRootProperty + ".client-id");
        String clientSecret = env.getProperty(clientRootProperty + ".client-secret");
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .clientId(clientId).clientSecret(clientSecret).build();
    }

    @Bean
    @Qualifier("googleClientRegistration")
    ClientRegistration googleClientRegistration() {
        String clientRootProperty = "spring.security.oauth2.client.registration.facebook";
        String clientId = env.getProperty(clientRootProperty + ".client-id");
        String clientSecret = env.getProperty(clientRootProperty + ".client-secret");
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .clientId(clientId).clientSecret(clientSecret).build();
    }

    @Bean
    @Qualifier("facebookOAuth2Service")
    OAuth2Service facebookOAuth2Service(
            FacebookOAuth2Authentication facebookOAuth2Authentication,
            UserRepository userRepository) {
        return new OAuth2Service(facebookOAuth2Authentication, userRepository);
    }

    @Bean
    @Qualifier("googleOAuth2Service")
    OAuth2Service googleOAuth2Service(
            GoogleOAuth2Authentication googleOAuth2Authentication,
            UserRepository userRepository) {
        return new OAuth2Service(googleOAuth2Authentication, userRepository);
    }
}
