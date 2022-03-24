package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;
import com.tomo.mcauthentication.domain.users.User;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class EmailLoginCommandHandler extends BaseLoginCommandHandler implements ResultableCommandHandler<EmailLoginCommand, SessionDto> {

    EmailAuthenticationService authenticationService;

    public EmailLoginCommandHandler(
            EmailAuthenticationService emailAuthenticationService,
            @Qualifier("sessionRepositoryJpaAdapter") SessionRepository sessionRepository,
            @Qualifier("jwtTokenProvider") TokenProvider tokenProvider,
            ModelMapper modelMapper) {
        super(modelMapper, sessionRepository, tokenProvider);
        this.authenticationService = emailAuthenticationService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public SessionDto handle(EmailLoginCommand command) {
        User user = authenticationService.authenticate(command.getEmail(), command.getPassword());
        Session session = new Session(
                sessionRepository.nextIdentity(),
                user,tokenProvider,
                command.getRememberMe(),
                command.getUserAgent(), command.getIpAddress());

        sessionRepository.save(session);
        return toDto(session);
    }
}
