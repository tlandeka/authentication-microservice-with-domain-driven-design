package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class RegisterNewUserCommandHandler extends AbstractVoidyCommandHandler<RegisterNewUserCommand> {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRepository;

    public RegisterNewUserCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRepository userRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRepository = userRepository;
    }

    @Override
    protected void abstractHandle(RegisterNewUserCommand aCommand) {
        UserRegistration userRegistration = UserRegistration.registerNewUser(
                aCommand.getPassword(),
                aCommand.getEmail(),
                aCommand.getFirstName(),
                aCommand.getLastName()
        );

        userRegistrationRepository.save(userRegistration);
        //todo send email
    }
}
