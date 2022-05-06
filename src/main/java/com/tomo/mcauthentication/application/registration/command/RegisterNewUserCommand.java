package com.tomo.mcauthentication.application.registration.command;

import com.tomo.mcauthentication.application.contracts.BaseCommand;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterNewUserCommand extends BaseCommand {

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @NotNull
    String email;

    @NotNull
    String password;

    public RegisterNewUserCommand(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
