package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class FacebookLoginCommandHandlerTest extends AbstractApplicationServiceTest {

    @Test
    @Transactional
    public void testNewFacebookUserCreated() {
        assertNotNull(createFacbookUser());
    }

    @Test
    @Transactional
    public void testNewFacebookUserFailedWhenGoogleUserExists() {
        createGoogleUser();
        assertThrows(BusinessRuleValidationException.class, this::createFacbookUser);
    }
}
