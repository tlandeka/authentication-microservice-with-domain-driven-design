package com.tomo.mcauthentication.application.users.command;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthorizeCommand;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeUserDetailsCommand extends AbstractAuthorizeCommand {

    private UUID userId;
    private String firstName;
    private String lastName;

    public ChangeUserDetailsCommand(UUID userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public List<String> getAuthorities() {
        List<String> authorities = super.getAuthorities();
        authorities.add("ADMIN");
        return authorities;
    }
}
