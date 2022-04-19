package com.tomo.mcauthentication.integration.application.recovery;

import com.tomo.mcauthentication.application.authentication.SessionAuthenticationCommandHandler;
import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.recovery.CreatePasswordRecoveryCodeCommandHandler;
import com.tomo.mcauthentication.application.recovery.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.domain.session.SessionAuthenticationService;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

public class CreatePasswordRecoveryCodeCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    CreatePasswordRecoveryCodeCommandHandler commandHandler;

    @Test
    @Transactional
    public void testRecoveryCodeCreated() {
        formLogin();
        RecoveryPasswordDto recoveryPasswordDto = commandHandler.handle(new CreatePasswordRecoveryCodeCommand(email()));
        assertNotNull(recoveryPasswordDto);
    }
}
