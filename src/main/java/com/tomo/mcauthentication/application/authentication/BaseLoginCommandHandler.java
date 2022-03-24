package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.domain.session.Session;
import com.tomo.mcauthentication.domain.session.SessionRepository;
import com.tomo.mcauthentication.domain.session.TokenProvider;

import org.modelmapper.ModelMapper;

public class BaseLoginCommandHandler {
    protected ModelMapper modelMapper;
    protected SessionRepository sessionRepository;
    protected TokenProvider tokenProvider;

    public BaseLoginCommandHandler() {
    }

    public BaseLoginCommandHandler(
            ModelMapper modelMapper,
            SessionRepository sessionRepository,
            TokenProvider tokenProvider) {
        this.modelMapper = modelMapper;
        this.sessionRepository = sessionRepository;
        this.tokenProvider = tokenProvider;
    }

    protected SessionDto toDto(Session session) {
        SessionDto dto = modelMapper.map(session, SessionDto.class);
        dto.setSessionId(session.getSessionId());
        return dto;
    }
}
