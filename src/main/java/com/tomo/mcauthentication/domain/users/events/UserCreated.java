package com.tomo.mcauthentication.domain.users.events;

import com.tomo.mcauthentication.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.registration.UserRegistrationStatus;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreated extends BaseDomainEvent  {
    UserId userId;
    String firstName;
    String lastName;
    String email;
    User.AuthProvider provider;

    public UserCreated(UserId userId, String firstName, String lastName, String email, User.AuthProvider provider) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.provider = provider;
    }
}
