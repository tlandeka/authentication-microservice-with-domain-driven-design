package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class GoogleLoginCommandHandlerTest extends ApplicationServiceTest {

    @Test
    @Transactional
    public void testNewGoogleUserCreated() {
        assertNotNull(createGoogleUser());
    }

    @Test
    @Transactional
    public void testNewGoogleUserFailedWhenFacebookUserExists() {
        createFacbookUser();
        assertThrows(BusinessRuleValidationException.class, this::createGoogleUser);
    }
}
