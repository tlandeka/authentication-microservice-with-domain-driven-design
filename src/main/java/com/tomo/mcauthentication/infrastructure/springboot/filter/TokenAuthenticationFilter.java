package com.tomo.mcauthentication.infrastructure.springboot.filter;

import com.tomo.mcauthentication.application.authentication.SessionAuthenticationCommandHandler;
import com.tomo.mcauthentication.application.authentication.command.SessionAuthenticationCommand;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;
import com.tomo.mcauthentication.infrastructure.util.CookieUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private SessionAuthenticationCommandHandler customUserDetailsService;

    @Autowired
    McAuthenticationModule mcAuthenticationModule;

    @Autowired
    protected AppProperties properties;

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                //ovdje postavi authentikaciju, stavim UserId, SessionId, AccessToken

                //onda u customDijelu radim hasRole gdje cu gadjati authorization service za toga usera.

                SessionDto userDetails = (SessionDto) mcAuthenticationModule.executeCommand(new SessionAuthenticationCommand(jwt));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Collections.
                        singletonList(new SimpleGrantedAuthority("ROLE_USER")));

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        return CookieUtils.getCookie(request, properties.getAuth().getSessionAuthTokenName())
                .map(cookie -> CookieUtils.deserialize(cookie, String.class))
                .filter(cookie -> StringUtils.hasText(cookie) && cookie.startsWith("Bearer "))
                .map(cookie -> cookie.substring(7))
                .orElse(null);

    }
}
