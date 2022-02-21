package com.tomo.mcauthentication.application.authentication.facebook;

import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.userregistration.confirm_user_registration.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.SessionService;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Service;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistration;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationRepository;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRespository;

import org.springframework.stereotype.Component;

@Component
public class FacebookLoginCommandHandler implements ResultableCommandHandler<FacebookLoginCommand, BaseUserDto> {

    OAuth2Service oAuth2Service;
    SessionService sessionService;

    public FacebookLoginCommandHandler(OAuth2Service oAuth2Service, SessionService sessionService) {
        this.oAuth2Service = oAuth2Service;
        this.sessionService = sessionService;
    }

    @Override
    public BaseUserDto handle(FacebookLoginCommand command) {
        User user = oAuth2Service.tryRegister(command.getAccessCode());
        sessionService.login(user);
        return null;
    }
}
