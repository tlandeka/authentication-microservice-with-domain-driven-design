package com.tomo.mcauthentication.integration.application.user.registration;

import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommand;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.infrastructure.persistence.UserRespositoryJpaAdapter;
import com.tomo.mcauthentication.integration.application.ApplicationServiceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;

public class ConfirmUserRegistrationCommandHandlerTest extends ApplicationServiceTest {

    @Autowired
    ConfirmUserRegistrationCommandHandler commandHandler;

    @Autowired
    RegisterNewUserCommandHandler registerNewUserCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Autowired
    UserRespositoryJpaAdapter userRespositoryJpaAdapter;

    @Test
    @Transactional
    public void testConfirmUserRegistration() {
        User user = createFormUser();
        assertNotNull(user);
    }
}
