package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

@Component
public class EmailLoginCommandHandler implements ResultableCommandHandler<EmailLoginCommand, BaseUserDto> {

    UserRegistrationRepository userRegistrationRepository;
    UserRespository userRespository;

    public EmailLoginCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRespository userRespository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRespository = userRespository;
    }

    @Override
    public BaseUserDto handle(EmailLoginCommand command) {
        return null;
    }
}
