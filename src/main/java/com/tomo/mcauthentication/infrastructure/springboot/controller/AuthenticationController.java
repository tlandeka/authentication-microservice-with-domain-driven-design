package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.FacebookLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.GoogleLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.infrastructure.springboot.security.UserAuthPrincipal;
import com.tomo.mcauthentication.infrastructure.springboot.security.UserAuthToken;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/")
public class AuthenticationController extends AbstractController {

    /**
     * Register User
     *
     * @param command
     */
    @RequestMapping(method = RequestMethod.POST, path = "/login/form")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity formLogin(HttpServletResponse response, @AuthenticationPrincipal UserAuthPrincipal user, @RequestBody @Validated EmailLoginCommand command) {
        if (user != null) {
           return ResponseEntity.ok(user.getSession());
        }

        SessionDto dto = this.executeCommand(command, SessionDto.class);

        CookieUtils.addCookie(
                response,
                properties.getAuth().getSessionAuthTokenName(),
                CookieUtils.serialize(dto.getAccessToken()),
                (int) TimeUnit.MILLISECONDS.toSeconds(properties.getAuth().getTokenExpirationMsec()));

        return ResponseEntity.ok(dto);
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
