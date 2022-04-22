package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.registration.command.ConfirmUserRegistrationCommand;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping(path = "/")
public class RegistrationController extends AbstractController {

    @Autowired
    CustomOAuth2UserService customOAuth2UserService;

    /**
     * Register User
     *
     * @param command
     */
    @RequestMapping(method = RequestMethod.POST, path = "/register/form")
    @ResponseStatus(HttpStatus.CREATED)
    public void formRegister(@RequestBody @Validated RegisterNewUserCommand command){
         this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register/confirm/")
    @ResponseStatus(HttpStatus.CREATED)
    public void formRegisterConfirmation(@RequestParam String confirmationCode){
        this.executeCommand(new ConfirmUserRegistrationCommand(confirmationCode));
    }

    @GetMapping("/random")
    public ResponseEntity<?> authenticateUser(HttpServletRequest request, HttpServletResponse response) {
        Optional<Cookie> cookie = CookieUtils.getCookie(request, "tomo_landeka");
        if (cookie.isPresent()) {
            int a = 5;
        }
        CookieUtils.addCookie(response, "tomo_landeka", "tomo", 60);
        return ResponseEntity.ok(response);
    }

}
