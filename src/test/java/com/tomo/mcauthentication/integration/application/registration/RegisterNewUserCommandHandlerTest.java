package com.tomo.mcauthentication.integration.application.registration;

import com.tomo.mcauthentication.application.registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.registration.NewUserRegisteredEventHandler;
import com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.application.registration.SendRegistrationConfirmationEmailCommandHandler;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class RegisterNewUserCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    RegisterNewUserCommandHandler commandHandler;

    @Autowired
    ConfirmUserRegistrationCommandHandler confirmUserRegistrationCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    NewUserRegisteredEventHandler newUserRegisteredEventHandler;

    @Test
    @Transactional
    public void testNewUserRegistrationCreated() {
        UserRegistration userRegistration = createUserRegistration();
        assertNotNull(userRegistration);
        verify(this.sendRegistrationConfirmationEmailCommandHandler, Mockito.times(1)).handle(any());
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
