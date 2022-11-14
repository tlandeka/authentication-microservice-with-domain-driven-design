package com.tomo.mcauthentication.integration.application.recovery;

import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.recovery.CreatePasswordRecoveryCodeCommandHandler;
import com.tomo.mcauthentication.application.recovery.GetUserRegistrationWithRecoveryCodeQueryHandler;
import com.tomo.mcauthentication.application.recovery.dto.GetUserRegistrationWithRecoveryCodeQuery;
import com.tomo.mcauthentication.application.registration.dto.UserRegistrationDto;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import com.tomo.mcauthentication.testdata.CommandObjectMother;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertNotNull;

public class GetUserRegistrationWithRecoveryCodeQueryHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    GetUserRegistrationWithRecoveryCodeQueryHandler queryHandler;

    @Autowired
    CreatePasswordRecoveryCodeCommandHandler commandHandler;

    @Test
    @Transactional
    public void testGetUserRegistrationWithRecoveryCode() {
        formLogin();
        RecoveryPasswordDto recoveryPasswordDto = commandHandler.handle(CommandObjectMother.createPasswordRecoveryCodeCommand());
        UserRegistrationDto userRegistrationDto = queryHandler.handle(new GetUserRegistrationWithRecoveryCodeQuery(recoveryPasswordDto.getRecoveryCode()));
        assertNotNull(userRegistrationDto.getEmail());
    }
}
