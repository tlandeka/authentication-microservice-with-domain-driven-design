package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SessionAuthenticationCommand extends BaseCommand {

    String accessToken;

    public SessionAuthenticationCommand(String accessToken) {
        this.accessToken = accessToken;
    }
}
