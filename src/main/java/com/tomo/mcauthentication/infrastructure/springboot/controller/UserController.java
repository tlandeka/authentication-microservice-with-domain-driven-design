package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.userregistration.register_new_user.RegisterNewUserCommand;
import com.tomo.mcauthentication.infrastructure.http.oauth2.CustomOAuth2UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestController
@RequestMapping(path = "/")
public class UserController {

    @Autowired
    private McAuthenticationModule authenticationModule;

    @Autowired
    @Qualifier("facebookClientRegistration")
    ClientRegistration clientRegistration;

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    /**
     * Register User
     *
     * @param command
     */
    @RequestMapping(method = RequestMethod.POST, path = "/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody @Validated RegisterNewUserCommand command){
         authenticationModule.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/facebook")
    @ResponseStatus(HttpStatus.CREATED)
    public void random(HttpServletRequest request){
        customOAuth2UserService.loadUser(new OAuth2UserRequest(clientRegistration, new OAuth2AccessToken(
                OAuth2AccessToken.TokenType.BEARER,
                "EAAC8IZAdZBTV0BAGsNumnmurG3clXddRZCzmkgSRIy2Ys6BEqYQpiYJWIpfeNGbOvWVoEH4qHEzcn9RoOaZC42JtkvE4A81ZCgRBtW6CKSkmDtASPFRnaIJhiLWAuQ8wNvCcTuFT2AiYFiNjCd4qj74GRAHTZBq3hcq9izTQrsZAuHLeUy8eO5OuC19LTNi1WoAjihA1QMfZB8iv7arcZBMo1iBUlakLxhkoZD",
                Instant.now(), Instant.now().plusSeconds(10000L))));
        int a = 5;
    }

}
