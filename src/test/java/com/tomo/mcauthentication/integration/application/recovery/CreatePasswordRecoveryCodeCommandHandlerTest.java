package com.tomo.mcauthentication.integration.application.recovery;

import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.recovery.CreatePasswordRecoveryCodeCommandHandler;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import com.tomo.mcauthentication.testdata.CommandObjectMother;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class CreatePasswordRecoveryCodeCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    CreatePasswordRecoveryCodeCommandHandler commandHandler;

    @Test
    @Transactional
    public void testRecoveryCodeCreated() {
        formLogin();
        RecoveryPasswordDto recoveryPasswordDto = commandHandler.handle(CommandObjectMother.createPasswordRecoveryCodeCommand());
        assertNotNull(recoveryPasswordDto);
        verify(this.sendPasswordRecoveryEmailCommandHandler, Mockito.times(1)).handle(any());
    }
}
