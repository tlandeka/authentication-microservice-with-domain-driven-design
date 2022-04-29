package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.FacebookLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.GoogleLoginCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.infrastructure.springboot.security.CurrentUser;
import com.tomo.mcauthentication.infrastructure.springboot.security.UserAuthPrincipal;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(method = RequestMethod.POST, path = "/login/form")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity formLogin(@CurrentUser UserAuthPrincipal user,
            @RequestBody @Validated EmailLoginCommand command) {
        return ResponseEntity.ok(this.executeCommand(command, user));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login/facebook")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity facebookLogin(@CurrentUser UserAuthPrincipal user,
            @RequestBody @Validated FacebookLoginCommand command){
        return ResponseEntity.ok(this.executeCommand(command, user));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login/google")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity googleLogin(@CurrentUser UserAuthPrincipal user,
            @RequestBody @Validated GoogleLoginCommand command) {
        return ResponseEntity.ok(this.executeCommand(command, user));
    }

    protected SessionDto executeCommand(Command command, UserAuthPrincipal user) {
        if (user != null) {
            return user.getSession();
        }

        SessionDto session = super.executeCommand(command, SessionDto.class);
        addAccessTokenToCookie(session.getAccessToken());

        return session;
    }

    private void addAccessTokenToCookie(String accessToken) {
        CookieUtils.addCookie(
                response,
                properties.getAuth().getSessionAuthTokenName(),
                CookieUtils.serialize(accessToken),
                (int) TimeUnit.MILLISECONDS.toSeconds(properties.getAuth().getTokenExpirationMsec()));
    }
}
