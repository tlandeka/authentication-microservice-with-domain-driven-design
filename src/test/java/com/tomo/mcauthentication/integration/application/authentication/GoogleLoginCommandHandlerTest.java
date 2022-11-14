package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class GoogleLoginCommandHandlerTest extends AbstractApplicationServiceTest {

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
