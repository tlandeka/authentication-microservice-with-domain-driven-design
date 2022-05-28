package com.tomo.mcauthentication.application.authentication.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseLoginCommand extends BaseCommand {
    private Boolean rememberMe;
    private String userAgent;
    private String ipAddress;

    public BaseLoginCommand() {
        super();
    }
}
