package com.tomo.mcauthentication.integration.application.user.authentication;

import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class FacebookLoginCommandHandlerTest extends ApplicationServiceTest {

    @Test
    @Transactional
    public void testNewFacebookUserCreated() {
        assertNotNull(createFacbookUser());
    }

    @Test
    @Transactional
    public void testNewFacebookUserFailedWhenGoogleUserExists() {
        createGoogleUser();

        assertThrows(BusinessRuleValidationException.class, () -> {
            createFacbookUser();
        });
    }
}
