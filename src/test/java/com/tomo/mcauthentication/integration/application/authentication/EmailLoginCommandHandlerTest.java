package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class EmailLoginCommandHandlerTest extends AbstractApplicationServiceTest {

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
