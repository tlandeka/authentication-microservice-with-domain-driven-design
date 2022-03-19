package com.tomo.mcauthentication.application.userregistration;

import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.userregistration.command.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class ConfirmUserRegistrationCommandHandler implements ResultableCommandHandler<ConfirmUserRegistrationCommand, BaseUserDto> {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRespository;

    public ConfirmUserRegistrationCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRepository userRespository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRespository = userRespository;
    }

    @Override
    public BaseUserDto handle(ConfirmUserRegistrationCommand command) {
        UserRegistration userRegistration = userRegistrationRepository.findByConfirmLink(command.getConfirmationUrl());

        if (userRegistration == null) {
            throw new IllegalStateException(
                    String.format("UserRegistration with confirmation link %s cannot be found.", command.getConfirmationUrl())
            );
        }

        User user = userRegistration.createUser(userRespository);
        userRespository.save(user);
        return null;
    }
}
