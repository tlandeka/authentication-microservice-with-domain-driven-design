package com.tomo.mcauthentication.integration.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(value = "local")
public class RegistrationControllerTest {

//    @Autowired
//    private MockMvc mockMvc;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int randomServerPort;

    @Autowired
    RegisterNewUserCommandHandler commandHandler;

    @Test
    @Transactional
    public void testUserRegister() throws URISyntaxException {
        final String baseUrl = "http://localhost:"+randomServerPort+"/user/register/";
        URI uri = new URI(baseUrl);

        RegisterNewUserCommand command = new RegisterNewUserCommand("Tom", "Land", "random@email.com", "Aarandom##");
        HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.set("X-COM-PERSIST", "true");

        HttpEntity<RegisterNewUserCommand> request = new HttpEntity<>(command, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);

        //Verify request succeed
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
}
