package com.tomo.mcauthentication.infrastructure.springboot.filter;

import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRepository;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;
import com.tomo.mcauthentication.infrastructure.springboot.security.UserAuthPrincipal;
import com.tomo.mcauthentication.infrastructure.springboot.security.UserAuthToken;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    McAuthenticationModule mcAuthenticationModuleExecutor;

    @Autowired
    protected AppProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                //maybe set authentication, UserId, SessionId, AccessToken
                //But only if the intention is to use hasRole, isAuthenticate on RestController

                SessionDto sessionDetails = (SessionDto) mcAuthenticationModuleExecutor.executeCommand(new SessionAuthenticationCommand(jwt));
                jwt = sessionDetails.getAccessToken();

                if (!sessionDetails.getAccessToken().equals(jwt)) {
                    CookieUtils.updateCookie(
                            request,
                            response,
                            properties.getAuth().getSessionAuthTokenName(),
                            CookieUtils.serialize(sessionDetails.getAccessToken()),
                            (int) TimeUnit.MILLISECONDS.toSeconds(properties.getAuth().getTokenExpirationMsec()));
                }

                setAuthentication(sessionDetails);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        filterChain.doFilter(request, response);
    }

    private void setAuthentication(SessionDto sessionDetails) {
        UserAuthToken currentAuthentication = null;

        try {
            currentAuthentication = (UserAuthToken) SecurityContextHolder.getContext().getAuthentication();
        } catch (Exception e) {
            currentAuthentication = null;
        }

        UserAuthToken newAuthentication = null;

        if (currentAuthentication == null) {
            newAuthentication = new UserAuthToken(new UserAuthPrincipal(sessionDetails));
        } else {
            UserAuthPrincipal principal = (UserAuthPrincipal) currentAuthentication.getPrincipal();
            if (!principal.getSession().getAccessToken().equals(sessionDetails.getAccessToken())) {
                newAuthentication = new UserAuthToken(new UserAuthPrincipal(sessionDetails));
            }
        }

        if (newAuthentication != null) {
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, properties.getAuth().getSessionAuthTokenName())
                .map(cookie -> CookieUtils.deserialize(cookie, String.class))
//                .filter(cookie -> StringUtils.hasText(cookie) && cookie.startsWith("Bearer "))
//                .map(cookie -> cookie.substring(7))
                .orElse(null);
    }
}
