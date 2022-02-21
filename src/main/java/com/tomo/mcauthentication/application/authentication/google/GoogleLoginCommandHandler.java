package com.tomo.mcauthentication.application.authentication.google;

import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

@Component
public class GoogleLoginCommandHandler implements ResultableCommandHandler<GoogleLoginCommand, BaseUserDto> {

    UserRegistrationRepository userRegistrationRepository;
    UserRespository userRespository;

    public GoogleLoginCommandHandler(
            UserRegistrationRepository userRegistrationRepository,
            UserRespository userRespository) {
        this.userRegistrationRepository = userRegistrationRepository;
        this.userRespository = userRespository;
    }

    @Override
    public BaseUserDto handle(GoogleLoginCommand command) {
        return null;
    }
}
