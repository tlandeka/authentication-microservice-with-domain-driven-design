package com.tomo.mcauthentication.integration.application.authentication;

import com.tomo.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.application.authentication.LogoutCommandHandler;
import com.tomo.mcauthentication.application.authentication.command.LogoutCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertThrows;

public class LogoutCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    LogoutCommandHandler logoutCommandHandler;

    @Autowired
    SessionAuthenticationService sessionAuthenticationService;

    @Test
    @Transactional
    public void testLogout() {
        SessionDto sessionDto = formLogin();
        logoutCommandHandler.handle(new LogoutCommand(sessionDto.getAccessToken()));
        assertThrows(BusinessRuleValidationException.class, () -> sessionAuthenticationService.authenticate(sessionDto.getAccessToken()));
    }
}
