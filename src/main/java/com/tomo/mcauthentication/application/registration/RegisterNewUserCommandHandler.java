package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class RegisterNewUserCommandHandler implements CommandHandler<RegisterNewUserCommand> {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRespository;

    public RegisterNewUserCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRepository userRespository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRespository = userRespository;
    }

    public void handle(RegisterNewUserCommand aCommand) {
        UserRegistration userRegistration = UserRegistration.registerNewUser(
                aCommand.getPassword(),
                aCommand.getEmail(),
                aCommand.getFirstName(),
                aCommand.getLastName(),
                userRegistrationRepository,
                userRespository
        );

        userRegistrationRepository.save(userRegistration);
    }
}
