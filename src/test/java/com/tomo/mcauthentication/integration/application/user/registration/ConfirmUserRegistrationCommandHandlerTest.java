package com.tomo.mcauthentication.integration.application.user.registration;

import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommand;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.infrastructure.persistence.UserRespositoryJpaAdapter;

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

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class ConfirmUserRegistrationCommandHandlerTest {

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
        registerNewUserCommandHandler.handle(new RegisterNewUserCommand("ac", "bc", "tomo@gmail.com", "AA123bb##"));
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findAllByEmail(Arrays.asList("tomo@gmail.com")).stream().findFirst();
        commandHandler.handle(new ConfirmUserRegistrationCommand(userRegistration.get().getConfirmLink()));
        User user = userRespositoryJpaAdapter.findByEmail("tomo@gmail.com");
        assertNotNull(user);
    }
}
