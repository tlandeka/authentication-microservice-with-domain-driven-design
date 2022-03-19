package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.GoogleLoginCommand;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Service;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.users.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GoogleLoginCommandHandler implements ResultableCommandHandler<GoogleLoginCommand, BaseUserDto> {

    OAuth2Service oAuth2Service;
    TokenProvider tokenProvider;
    SessionRepository sessionRepository;

    public GoogleLoginCommandHandler(
            @Qualifier("googleOAuth2Service") OAuth2Service oAuth2Service,
            @Qualifier("jwtTokenProvider") TokenProvider tokenProvider,
            @Qualifier("sessionRespositoryJpaAdapter") SessionRepository sessionRepository) {
        this.oAuth2Service = oAuth2Service;
        this.tokenProvider = tokenProvider;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public BaseUserDto handle(GoogleLoginCommand command) {
        User user = oAuth2Service.registerAuthenticate(command.getAccessCode());
        Session session = new Session(
                sessionRepository.nextIdentity(),
                user,tokenProvider,
                command.getRememberMe(),
                command.getUserAgent(), command.getIpAddress());
        sessionRepository.save(session);
        return null;
    }
}
