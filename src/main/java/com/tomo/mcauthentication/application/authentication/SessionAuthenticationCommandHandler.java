package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationCommandHandler implements ResultableCommandHandler<SessionAuthenticationCommand, BaseUserDto> {

    SessionAuthenticationService sessionAuthenticationService;

    public SessionAuthenticationCommandHandler(SessionAuthenticationService sessionAuthenticationService) {
        this.sessionAuthenticationService = sessionAuthenticationService;
    }

    @Override
    public BaseUserDto handle(SessionAuthenticationCommand command) {
        Session session = sessionAuthenticationService.authenticate(command.getAuthToken());
        return null;
    }
}
