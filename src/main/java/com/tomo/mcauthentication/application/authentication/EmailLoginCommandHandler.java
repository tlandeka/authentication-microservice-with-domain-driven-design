package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;
import com.tomo.mcauthentication.domain.users.User;

import org.springframework.stereotype.Component;

@Component
public class EmailLoginCommandHandler implements ResultableCommandHandler<EmailLoginCommand, BaseUserDto> {

    EmailAuthenticationService authenticationService;
    SessionRepository sessionRepository;
    TokenProvider tokenProvider;

    public EmailLoginCommandHandler(EmailAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public BaseUserDto handle(EmailLoginCommand command) {
        User user = authenticationService.authenticate(command.getEmail(), command.getPassword());
        Session session = new Session(
                sessionRepository.nextIdentity(),
                user,tokenProvider,
                command.getRememberMe(),
                command.getUserAgent(), command.getIpAddress());

        sessionRepository.save(session);
        return null;
    }
}
