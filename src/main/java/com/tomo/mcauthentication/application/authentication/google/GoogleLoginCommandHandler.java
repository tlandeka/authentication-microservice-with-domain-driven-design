package com.tomo.mcauthentication.application.authentication.google;

import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.SessionService;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Service;
import com.tomo.mcauthentication.domain.users.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GoogleLoginCommandHandler implements ResultableCommandHandler<GoogleLoginCommand, BaseUserDto> {

    OAuth2Service oAuth2Service;
    SessionService sessionService;

    public GoogleLoginCommandHandler(
            @Qualifier("googleOAuth2Service") OAuth2Service oAuth2Service,
            SessionService sessionService) {
        this.oAuth2Service = oAuth2Service;
        this.sessionService = sessionService;
    }

    @Override
    public BaseUserDto handle(GoogleLoginCommand command) {
        User user = oAuth2Service.tryRegister(command.getAccessCode());
        sessionService.login(user);
        return null;
    }
}
