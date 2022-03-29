package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class EmailLoginCommandHandlerTest extends ApplicationServiceTest {

    @Test
    @Transactional
    public void testUserFormLogin() {
        assertNotNull(formLogin());
    }

    @Test
    @Transactional
    public void testNewFormUserFailedWhenGoogleUserExists() {
        createGoogleUser();
        assertThrows(BusinessRuleValidationException.class, this::createFormUser);
    }
}
