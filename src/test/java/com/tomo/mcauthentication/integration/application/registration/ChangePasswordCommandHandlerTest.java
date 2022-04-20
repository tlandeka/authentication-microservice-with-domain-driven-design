package com.tomo.mcauthentication.integration.application.registration;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.Voidy;
import com.tomo.mcauthentication.application.registration.ChangePasswordCommandHandler;
import com.tomo.mcauthentication.application.registration.command.ChangePasswordCommand;
import com.tomo.mcauthentication.ddd.domain.BusinessRuleValidationException;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import com.tomo.mcauthentication.testdata.StaticFields;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ChangePasswordCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    ChangePasswordCommandHandler commandHandler;

    @Test
    @Transactional
    public void testChangePassword() {
        SessionDto sessionDto = formLogin();

        assertEquals(commandHandler.handle(new ChangePasswordCommand(
                sessionDto.getAccessToken(),
                        StaticFields.PASSWORD,
                        StaticFields.NEW_PASS,
                        StaticFields.NEW_PASS)).getClass(),
                Voidy.class);


        assertThrows(BusinessRuleValidationException.class, () -> emailLoginCommandHandler.handle(new EmailLoginCommand(StaticFields.USER_EMAIL, StaticFields.PASSWORD)));

        assertEquals(
                emailLoginCommandHandler.handle(new EmailLoginCommand(StaticFields.USER_EMAIL, StaticFields.NEW_PASS)).getClass(),
                SessionDto.class
        );
    }
}
