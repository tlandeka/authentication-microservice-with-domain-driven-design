package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.contracts.Command;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.contracts.Query;
import com.tomo.mcauthentication.application.contracts.Request;
import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.application.contracts.security.Authenticate;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController {

    @Autowired
    protected McAuthenticationModule authenticationModule;

    @Autowired
    protected AppProperties properties;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected TokenProvider tokenProvider;

    protected Response executeCommand(Command command) {
        this.setAuthToken(command);
        return authenticationModule.executeCommand(command);
    }

    protected <T extends Response> T executeCommand(Command command, Class<T> tclass) {
        this.setAuthToken(command);
        return tclass.cast(authenticationModule.executeCommand(command));
    }

    protected <T extends Response> T executeQuery(Query query, Class<T> tclass) {
        this.setAuthToken(query);
        return tclass.cast(authenticationModule.executeQuery(query));
    }

    private void setAuthToken(Request request) {
        if (request instanceof Authenticate) {
            String jwt = getJwtFromRequest();
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                ((Authenticate) request).setAuthToken(jwt);
            }
        }
    }

    private String getJwtFromRequest() {
        return CookieUtils.getCookie(request, properties.getAuth().getSessionAuthTokenName())
                .map(cookie -> CookieUtils.deserialize(cookie, String.class))
//                .filter(cookie -> StringUtils.hasText(cookie) && cookie.startsWith("Bearer "))
//                .map(cookie -> cookie.substring(7))
                .orElse(null);
    }
}
