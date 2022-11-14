package com.tomo.mcauthentication.domain.users.events;

import com.tomo.ddd.domain.BaseDomainEvent;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserNameChanged extends BaseDomainEvent  {
    String email;
    String firstName;
    String lastName;

    public UserNameChanged(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
