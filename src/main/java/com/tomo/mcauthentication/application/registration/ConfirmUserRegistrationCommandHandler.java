package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.application.registration.command.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.users.dto.BaseUserDto;
import com.tomo.mcauthentication.domain.registration.UserRegistration;
import com.tomo.mcauthentication.domain.registration.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Component;

@Component
public class ConfirmUserRegistrationCommandHandler implements CommandHandler<ConfirmUserRegistrationCommand, BaseUserDto> {

    UserRegistrationRepository userRegistrationRepository;
    UserRepository userRepository;

    public ConfirmUserRegistrationCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRepository userRepository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BaseUserDto handle(ConfirmUserRegistrationCommand command) {
        UserRegistration userRegistration = userRegistrationRepository.findByConfirmLink(command.getConfirmationUrl());

        if (userRegistration == null) {
            throw new IllegalStateException(
                    String.format("UserRegistration with confirmation link %s cannot be found.", command.getConfirmationUrl())
            );
        }

        User user = userRegistration.createUser(userRepository);
        userRegistration.setUserId(user.getUserId());

        userRepository.save(user);
        userRegistrationRepository.save(userRegistration);
        return null;
    }
}
