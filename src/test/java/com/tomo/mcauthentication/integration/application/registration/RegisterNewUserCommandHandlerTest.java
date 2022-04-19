package com.tomo.mcauthentication.integration.application.registration;

import com.tomo.mcauthentication.application.registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class RegisterNewUserCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    RegisterNewUserCommandHandler commandHandler;

    @Autowired
    ConfirmUserRegistrationCommandHandler confirmUserRegistrationCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Test
    @Transactional
    public void testNewUserRegistrationCreated() {
        UserRegistration userRegistration = createUserRegistration();
        assertNotNull(userRegistration);
    }

    @Test
    @Transactional
    public void testNewUserRegistrationFailedWhenUserExists() {
        createFormUser();

        assertThrows(RuntimeException.class, () -> {
            createUserRegistration();
        });
    }
}
