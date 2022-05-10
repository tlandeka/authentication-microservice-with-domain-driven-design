package com.tomo.mcauthentication.domain;

import com.tomo.mcauthentication.domain.registration.PasswordService;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.registration.rules.UserRegistrationMustBeUnique;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.domain.users.rules.UserEmailMustBeUnique;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DomainRegistry implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static EncryptionService encryptionService() {
        return (EncryptionService) applicationContext.getBean("MD5EncryptionService");
    }

    public static PasswordService passwordService() {
        return (PasswordService) applicationContext.getBean("passwordService");
    }

    public static TokenProvider tokenProvider() {
        return (TokenProvider) applicationContext.getBean("jwtTokenProvider");
    }

    public static ModelMapper modelMapper() {
        return (ModelMapper) applicationContext.getBean("modelMapper");
    }

    public static UserRepository userRepository() {
        return (UserRepository) applicationContext.getBean("userRepository");
    }

    public static UserRegistrationRepository userRegistrationRepository() {
        return (UserRegistrationRepository) applicationContext.getBean("userRegistrationRepository");
    }

    public static UserEmailMustBeUnique userEmailMustBeUnique(String anEmail) {
        return new UserEmailMustBeUnique(userRepository(), anEmail);
    }

    public static UserRegistrationMustBeUnique userRegistrationMustBeUnique(String anEmail) {
        return new UserRegistrationMustBeUnique(userRegistrationRepository(), anEmail);
    }



    @Override
    public void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = anApplicationContext;
        }
    }
}
