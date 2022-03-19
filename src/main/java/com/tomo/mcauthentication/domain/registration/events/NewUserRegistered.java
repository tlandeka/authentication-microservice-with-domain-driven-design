package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.registration.UserRegistrationId;

public class NewUserRegistered extends BaseDomainEvent  {
    private UserRegistrationId userRegistrationId;
    private String username;
    private String password;
}
