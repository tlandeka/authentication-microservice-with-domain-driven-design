package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LogoutCommand extends BaseCommand {
    private String accessToken;

    public LogoutCommand(String authToken) {
        this.accessToken = authToken;
    }
}
