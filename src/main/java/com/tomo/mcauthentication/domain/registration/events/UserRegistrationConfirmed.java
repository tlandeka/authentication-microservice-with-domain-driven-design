package com.tomo.mcauthentication.domain.registration.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.users.UserId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationConfirmed extends BaseDomainEvent  {
    private long userRegistrationId;
    private UserRegistrationStatus status;
    private UserId userId;

    public UserRegistrationConfirmed(long userRegistrationId, UserRegistrationStatus status, UserId userId) {
        this.userRegistrationId = userRegistrationId;
        this.status = status;
        this.userId = userId;
    }
}
