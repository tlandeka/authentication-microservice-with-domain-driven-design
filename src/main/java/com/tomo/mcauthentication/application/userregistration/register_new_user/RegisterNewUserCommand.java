package com.tomo.mcauthentication.application.userregistration.register_new_user;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import java.util.UUID;

public class RegisterNewUserCommand extends BaseCommand {
    String firstName;
    String lastName;
    String email;
    String password;

    public RegisterNewUserCommand() {
        super(UUID.randomUUID());
    }

    public RegisterNewUserCommand(String firstName, String lastName, String email, String password) {
        super(UUID.randomUUID());
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public RegisterNewUserCommand(UUID id) {
        super(id);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
