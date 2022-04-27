package com.tomo.mcauthentication.application.contracts.security;

import java.util.List;

public interface Authorize extends Authenticate {

    default List<String> getAuthorities() {
        return List.of("USER");
    }

}
