package com.tomo.mcauthentication.domain;

import com.tomo.mcauthentication.domain.registration.PasswordService;

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

    @Override
    public void setApplicationContext(ApplicationContext anApplicationContext) throws BeansException {
        if (DomainRegistry.applicationContext == null) {
            DomainRegistry.applicationContext = anApplicationContext;
        }
    }
}
