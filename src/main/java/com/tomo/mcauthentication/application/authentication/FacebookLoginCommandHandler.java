package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.FacebookLoginCommand;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.oauth2.OAuth2Service;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.users.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FacebookLoginCommandHandler extends BaseLoginCommandHandler implements ResultableCommandHandler<FacebookLoginCommand, BaseUserDto> {

    OAuth2Service oAuth2Service;

    public FacebookLoginCommandHandler(
            @Qualifier("facebookOAuth2Service") OAuth2Service oAuth2Service,
            @Qualifier("jwtTokenProvider") TokenProvider tokenProvider,
            @Qualifier("sessionRepositoryJpaAdapter") SessionRepository sessionRepository,
            ModelMapper modelMapper) {
        super(modelMapper, sessionRepository, tokenProvider);
        this.oAuth2Service = oAuth2Service;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public BaseUserDto handle(FacebookLoginCommand command) {
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
