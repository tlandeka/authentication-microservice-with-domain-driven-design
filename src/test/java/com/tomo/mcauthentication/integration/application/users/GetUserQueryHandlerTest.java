package com.tomo.mcauthentication.integration.application.users;

import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.users.GetUserQueryHandler;
import com.tomo.mcauthentication.application.users.query.GetUserQuery;
import com.tomo.mcauthentication.integration.application.AbstractApplicationServiceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class GetUserQueryHandlerTest extends AbstractApplicationServiceTest {

    @Autowired
    GetUserQueryHandler getUserQueryHandler;

    @Test
    @Transactional
    public void testGetUser() {
        SessionDto sessionDto = formLogin();

        GetUserQuery query = new GetUserQuery();
        query.setUserId(UUID.fromString(sessionDto.getUserId()));
        assertNotNull(getUserQueryHandler.handle(query));
    }
}
