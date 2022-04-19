package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SessionAuthenticationCommandHandler extends BaseMapper implements CommandHandler<SessionAuthenticationCommand, SessionDto> {

    SessionAuthenticationService sessionAuthenticationService;

    public SessionAuthenticationCommandHandler(
            SessionAuthenticationService sessionAuthenticationService,
            ModelMapper modelMapper) {
        super(modelMapper);
        this.sessionAuthenticationService = sessionAuthenticationService;
    }

    @Override
    public SessionDto handle(SessionAuthenticationCommand command) {
        return toDto(sessionAuthenticationService.authenticate(command.getAccessToken()), SessionDto.class);
    }
}
