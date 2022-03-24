package com.tomo.mcauthentication.integration.application.user.registration;

import com.tomo.mcauthentication.application.registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.infrastructure.persistence.UserRepositoryJpaAdapter;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

public class ConfirmUserRegistrationCommandHandlerTest extends ApplicationServiceTest {

    @Autowired
    ConfirmUserRegistrationCommandHandler commandHandler;

    @Autowired
    RegisterNewUserCommandHandler registerNewUserCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    UserRepositoryJpaAdapter userRespositoryJpaAdapter;

    @Test
    @Transactional
    public void testConfirmUserRegistration() {
        User user = createFormUser();
        assertNotNull(user);
    }
}
