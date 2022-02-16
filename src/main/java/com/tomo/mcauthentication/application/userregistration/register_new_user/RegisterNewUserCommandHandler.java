package com.tomo.mcauthentication.application.userregistration.register_new_user;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

@Component
public class RegisterNewUserCommandHandler implements CommandHandler<RegisterNewUserCommand> {

    UserRegistrationRepository userRegistrationRepository;
    UserRespository userRespository;

    public RegisterNewUserCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRespository userRespository) {
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

        userRegistrationRepository.add(userRegistration);
    }
}
