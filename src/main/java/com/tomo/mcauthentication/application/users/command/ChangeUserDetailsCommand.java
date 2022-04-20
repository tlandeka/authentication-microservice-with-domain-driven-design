package com.tomo.mcauthentication.application.users.command;

import com.tomo.mcauthentication.application.contracts.AuthorizeCommand;
import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeUserDetailsCommand extends AuthorizeCommand {

    private UUID userId;
    private String firstName;
    private String lastName;

    public ChangeUserDetailsCommand(UUID userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
