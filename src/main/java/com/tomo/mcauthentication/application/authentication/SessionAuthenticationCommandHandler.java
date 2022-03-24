package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.configuration.ResultableCommandHandler;
import com.tomo.mcauthentication.application.users.BaseUserDto;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationCommandHandler extends BaseLoginCommandHandler implements ResultableCommandHandler<SessionAuthenticationCommand, SessionDto> {

    SessionAuthenticationService sessionAuthenticationService;

    public SessionAuthenticationCommandHandler(SessionAuthenticationService sessionAuthenticationService) {
        this.sessionAuthenticationService = sessionAuthenticationService;
    }

    @Override
    public SessionDto handle(SessionAuthenticationCommand command) {
        return toDto(
                sessionAuthenticationService.authenticate(command.getAuthToken())
        );
    }
}
