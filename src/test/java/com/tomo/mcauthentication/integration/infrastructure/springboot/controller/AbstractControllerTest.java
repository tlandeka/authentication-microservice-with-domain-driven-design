package com.tomo.mcauthentication.integration.infrastructure.springboot.controller;

import com.tomo.mcauthentication.ddd.email.EmailSender;
import com.tomo.mcauthentication.integration.BaseIntegrationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;

public abstract class AbstractControllerTest extends BaseIntegrationTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    @MockBean
    protected EmailSender emailMessageSender;

    @LocalServerPort
    protected int randomServerPort;

    protected URI url(String uri) throws URISyntaxException {
        final String baseUrl = "http://localhost:" + randomServerPort+ uri;
        return new URI(baseUrl);
    }

    protected HttpHeaders baseHeaders() {
        HttpHeaders headers =  new org.springframework.http.HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        return headers;
    }
}
