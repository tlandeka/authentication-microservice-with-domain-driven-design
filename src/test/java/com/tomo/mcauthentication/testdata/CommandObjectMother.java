package com.tomo.mcauthentication.testdata;

import com.github.javafaker.Faker;
import com.tomo.mcauthentication.application.authentication.command.EmailLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.FacebookLoginCommand;
import com.tomo.mcauthentication.application.authentication.command.GoogleLoginCommand;
import com.tomo.mcauthentication.application.recovery.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;

public class CommandObjectMother extends StaticFields {

    private static Faker faker = new Faker();

    private CommandObjectMother() {
    }

    public static RegisterNewUserCommand registerNewUserCommand() {
        return new RegisterNewUserCommand(USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, PASSWORD);
    }

    public static RegisterNewUserCommand registerNewUserCommandWithFakerEmail() {
        return new RegisterNewUserCommand(USER_FIRST_NAME, USER_LAST_NAME, faker.internet().emailAddress(), PASSWORD);
    }

    public static EmailLoginCommand emailLoginCommand() {
        return new EmailLoginCommand(USER_EMAIL, PASSWORD);
    }

    public static FacebookLoginCommand facebookLoginCommand() {
        return new FacebookLoginCommand(ACCESS_CODE);
    }

    public static GoogleLoginCommand googleLoginCommand() {
        return new GoogleLoginCommand(ACCESS_CODE);
    }

    public static CreatePasswordRecoveryCodeCommand createPasswordRecoveryCodeCommand() {
        return new CreatePasswordRecoveryCodeCommand(USER_EMAIL);
    }


}
