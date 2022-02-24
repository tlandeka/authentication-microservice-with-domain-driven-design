package com.tomo.mcauthentication.integration.application.user.registration;

import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class RegisterNewUserCommandHandlerTest extends ApplicationServiceTest {

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
