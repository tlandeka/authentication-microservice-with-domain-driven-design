package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.FacebookLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.GoogleLoginCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.infrastructure.http.oauth2.CustomOAuth2UserService;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class AuthenticationController extends AbstractController {

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
    @RequestMapping(method = RequestMethod.POST, path = "/login/form")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity formLogin(HttpServletResponse response, @RequestBody @Validated EmailLoginCommand command){
        SessionDto dto = this.executeCommand(command, SessionDto.class);
        CookieUtils.addCookie(
                response,
                "session-id-kmee",
                CookieUtils.serialize(dto.getAccessToken()),
                (int) ((properties.getAuth().getTokenExpirationMsec() / 1000) % 60));

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login/facebook")
    @ResponseStatus(HttpStatus.CREATED)
    public void facebookLogin(@RequestBody @Validated FacebookLoginCommand command){
        this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login/google")
    @ResponseStatus(HttpStatus.CREATED)
    public void googleLogin(@RequestBody @Validated GoogleLoginCommand command) {
        this.executeCommand(command);
    }
}
