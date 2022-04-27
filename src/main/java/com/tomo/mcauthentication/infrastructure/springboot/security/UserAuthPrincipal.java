package com.tomo.mcauthentication.infrastructure.springboot.security;

import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.domain.users.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserAuthPrincipal implements UserDetails {

    private SessionDto session;

    private final Collection<GrantedAuthority> authorities;

    private boolean enabled = true;

    public UserAuthPrincipal(SessionDto session) {
        this.session = session;
        this.authorities = new ArrayList<>();
    }

    public SessionDto getSession() {
        return session;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return session.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
