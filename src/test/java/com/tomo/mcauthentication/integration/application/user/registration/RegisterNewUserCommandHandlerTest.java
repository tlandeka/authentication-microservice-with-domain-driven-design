package com.tomo.mcauthentication.integration.application.user.registration;

import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommandHandler;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommand;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class RegisterNewUserCommandHandlerTest {

    @Autowired
    RegisterNewUserCommandHandler commandHandler;

    @Autowired
    ConfirmUserRegistrationCommandHandler confirmUserRegistrationCommandHandler;

    @Autowired
    UserRegistrationRepository userRegistrationRepository;

    @Test
    @Transactional
    public void testNewUserRegistrationCreated() {
        commandHandler.handle(new RegisterNewUserCommand("ac", "bc", "tomo@gmail.com", "AA123bb##"));
        List<UserRegistration> userRegistrations = userRegistrationRepository.findAllByEmail(Arrays.asList("tomo@gmail.com"));
        assertTrue(userRegistrations.size() != 0);
    }

    @Test
    @Transactional
    public void testNewUserRegistrationFailedWhenUserExists() {
        commandHandler.handle(new RegisterNewUserCommand("ac", "bc", "tomo@gmail.com", "AA123bb##"));
        Optional<UserRegistration> userRegistration = userRegistrationRepository.findAllByEmail(Arrays.asList("tomo@gmail.com")).stream().findFirst();
        confirmUserRegistrationCommandHandler.handle(new ConfirmUserRegistrationCommand(userRegistration.get().getConfirmLink()));

        assertThrows(RuntimeException.class, () -> {
            commandHandler.handle(new RegisterNewUserCommand("ac", "bc", "tomo@gmail.com", "AA123bb##"));
        });
    }
}
