package com.tomo.mcauthentication.domain.user_registrations.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.user_registrations.UserRegistrationId;

public class NewUserRegistered extends BaseDomainEvent  {
    private UserRegistrationId userRegistrationId;
    private String username;
    private String password;
}
