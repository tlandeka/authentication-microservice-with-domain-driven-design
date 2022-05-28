package com.tomo.mcauthentication.integration.infrastructure.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.infrastructure.springboot.controller.RestApiRoutes.RegistrationRoutes;
import com.tomo.mcauthentication.testdata.CommandObjectMother;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest extends AbstractControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    @Transactional
//    public void testUserRegister() throws URISyntaxException {
//
//        RegisterNewUserCommand command = CommandObjectMother.registerNewUserCommandWithFakerEmail();
//        HttpHeaders headers = baseHeaders();
//        HttpEntity<RegisterNewUserCommand> request = new HttpEntity<>(command, headers);
//
//        ResponseEntity<String> result = this.restTemplate.postForEntity(
//                url(RegistrationRoutes.FORM_REGISTRATION),
//                request,
//                String.class);
//
//        //Verify request succeed
//        Assert.assertEquals(201, result.getStatusCodeValue());
//    }

    @Test
    @Transactional
    public void shouldCreateUserRegistration() throws Exception {
        RegisterNewUserCommand command = CommandObjectMother.registerNewUserCommandWithFakerEmail();
        this.mockMvc.perform(
                post(RegistrationRoutes.FORM_REGISTRATION)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(command)))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
