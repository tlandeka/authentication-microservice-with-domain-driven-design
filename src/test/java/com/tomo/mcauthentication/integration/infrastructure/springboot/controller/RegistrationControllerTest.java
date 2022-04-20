package com.tomo.mcauthentication.integration.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.testdata.CommandObjectMother;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.net.URISyntaxException;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class RegistrationControllerTest extends AbstractControllerTest {

    @Test
    @Transactional
    public void testUserRegister() throws URISyntaxException {

        RegisterNewUserCommand command = CommandObjectMother.registerNewUserCommandWithFakerEmail();
        HttpHeaders headers = baseHeaders();
        HttpEntity<RegisterNewUserCommand> request = new HttpEntity<>(command, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri(), request, String.class);

        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
}
