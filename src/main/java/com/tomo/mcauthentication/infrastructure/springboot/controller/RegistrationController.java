package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.recovery.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.recovery.command.UpdatePasswordWithRecoveryCodeCommand;
import com.tomo.mcauthentication.application.registration.command.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class RegistrationController extends AbstractController {

    @RequestMapping(method = RequestMethod.POST, path = "/register/form")
    @ResponseStatus(HttpStatus.CREATED)
    public void formRegister(@RequestBody @Validated RegisterNewUserCommand command){
         this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register/confirm/")
    @ResponseStatus(HttpStatus.OK)
    public void formRegisterConfirmation(@RequestParam String confirmationCode){
        this.executeCommand(new ConfirmUserRegistrationCommand(confirmationCode));
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/register/password/reset")
    @ResponseStatus(HttpStatus.OK)
    public void formRegisterRecovery(@RequestParam CreatePasswordRecoveryCodeCommand command){
        this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = "/register/password/reset/confirm")
    @ResponseStatus(HttpStatus.OK)
    public void passwordReset(@RequestParam UpdatePasswordWithRecoveryCodeCommand command){
        this.executeCommand(command);
    }
}
