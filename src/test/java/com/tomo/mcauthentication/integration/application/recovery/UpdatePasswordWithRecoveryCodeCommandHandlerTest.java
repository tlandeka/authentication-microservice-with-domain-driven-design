package com.tomo.mcauthentication.integration.application.recovery;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.Voidy;
import com.tomo.mcauthentication.application.recovery.CreatePasswordRecoveryCodeCommandHandler;
import com.tomo.mcauthentication.application.recovery.UpdatePasswordWithRecoveryCodeCommandHandler;
import com.tomo.mcauthentication.application.recovery.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.recovery.command.UpdatePasswordWithRecoveryCodeCommand;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class UpdatePasswordWithRecoveryCodeCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    CreatePasswordRecoveryCodeCommandHandler createPasswordRecoveryCodeCommandHandler;

    @Autowired
    UpdatePasswordWithRecoveryCodeCommandHandler commandHandler;

    @Test
    @Transactional
    public void testUpdatePasswordWithRecoveryCode() {
        formLogin();
        RecoveryPasswordDto recoveryPasswordDto = createPasswordRecoveryCodeCommandHandler.handle(new CreatePasswordRecoveryCodeCommand(email()));
        assertEquals(commandHandler.handle(new UpdatePasswordWithRecoveryCodeCommand(
                NEW_PASS,
                NEW_PASS,
                recoveryPasswordDto.getRecoveryCode())).getClass(),
                Voidy.class);

        assertEquals(
                emailLoginCommandHandler.handle(new EmailLoginCommand(email(), NEW_PASS)).getClass(),
                SessionDto.class
        );
    }
}
