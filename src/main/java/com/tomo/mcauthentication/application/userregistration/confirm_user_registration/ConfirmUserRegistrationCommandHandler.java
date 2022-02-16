package com.tomo.mcauthentication.application.userregistration.confirm_user_registration;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.Result;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

@Component
public class ConfirmUserRegistrationCommandHandler implements ResultableCommandHandler<ConfirmUserRegistrationCommand, BaseUserDto> {

    UserRegistrationRepository userRegistrationRepository;
    UserRespository userRespository;

    public ConfirmUserRegistrationCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRespository userRespository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRespository = userRespository;
    }

    @Override public BaseUserDto handle(ConfirmUserRegistrationCommand command) {
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
