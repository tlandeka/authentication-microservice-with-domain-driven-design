package com.tomo.mcauthentication.domain.users.events;

import com.tomo.ddd.domain.BaseDomainEvent;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;

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
