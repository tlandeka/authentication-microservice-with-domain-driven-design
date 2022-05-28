package com.tomo.mcauthentication.integration.application.users;

import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.users.ChangeUserDetailsCommandHandler;
import com.tomo.mcauthentication.application.users.command.ChangeUserDetailsCommand;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;
import com.tomo.mcauthentication.testdata.CommandObjectMother;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

public class ChangeUserDetailsCommandHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    ChangeUserDetailsCommandHandler commandHandler;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void testChangeUserDetails() {
        User user = createFormUser();
        SessionDto sessionDto = emailLoginCommandHandler.handle(CommandObjectMother.emailLoginCommand());

        ChangeUserDetailsCommand command = new ChangeUserDetailsCommand(
                user.getUserId().id(),
                "first name",
                "last name");
        command.setAuthToken(sessionDto.getAccessToken());

        commandHandler.handle(command);
        User userFromDb = userRepository.findById(user.getUserId());
        assertEquals(userFromDb.getFirstName(), "first name");
        assertEquals(userFromDb.getLastName(), "last name");
    }
}
