package com.tomo.mcauthentication.integration.application.registration;

import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

public class ConfirmUserRegistrationCommandHandlerTest extends AbstractApplicationServiceTest {

    @Test
    @Transactional
    public void testConfirmUserRegistration() {
        User user = createFormUser();
        assertNotNull(user);
    }
}
