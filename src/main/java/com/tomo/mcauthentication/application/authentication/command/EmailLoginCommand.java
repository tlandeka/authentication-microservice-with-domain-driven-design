package com.tomo.mcauthentication.application.authentication.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmailLoginCommand extends BaseLoginCommand {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private Boolean rememberMe;
    private String userAgent;
    private String ipAddress;

    public EmailLoginCommand() {
        super();
    }

    public EmailLoginCommand(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }
}
