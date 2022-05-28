package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.mcauthentication.application.authentication.SessionAuthenticationCommandHandler;
import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class SessionAuthenticationCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    SessionAuthenticationCommandHandler commandHandler;

    @Autowired
    SessionAuthenticationService sessionAuthenticationService;

    @Test
    @Transactional
    public void testAuthenticateSession() {
        SessionDto initialSessionDto = formLogin();
        SessionDto sessionDto = commandHandler.handle(new SessionAuthenticationCommand(initialSessionDto.getAccessToken()));
        assertEquals(initialSessionDto.getAccessToken(), sessionDto.getAccessToken());
    }
}
