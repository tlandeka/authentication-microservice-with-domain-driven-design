package com.tomo.mcauthentication.application.contracts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorizeCommand extends BaseCommand {
    String accessToken;

    public AuthorizeCommand(String accessToken) {
        this.accessToken = accessToken;
    }
}
