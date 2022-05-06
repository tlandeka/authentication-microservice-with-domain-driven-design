package com.tomo.mcauthentication.infrastructure.springboot.controller;

import com.tomo.mcauthentication.application.recovery.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.recovery.command.UpdatePasswordWithRecoveryCodeCommand;
import com.tomo.mcauthentication.application.registration.command.ConfirmUserRegistrationCommand;
import com.tomo.mcauthentication.application.registration.command.RegisterNewUserCommand;
import com.tomo.mcauthentication.infrastructure.springboot.controller.RestApiRoutes.RegistrationRoutes;

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

    @RequestMapping(method = RequestMethod.POST, path = RegistrationRoutes.FORM_REGISTRATION)
    @ResponseStatus(HttpStatus.CREATED)
    public void formRegister(@RequestBody @Validated RegisterNewUserCommand command){
         this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.POST, path = RegistrationRoutes.CONFIRM_REGISTRATION)
    @ResponseStatus(HttpStatus.OK)
    public void formRegisterConfirmation(@RequestParam String confirmationCode){
        this.executeCommand(new ConfirmUserRegistrationCommand(confirmationCode));
    }

    @RequestMapping(method = RequestMethod.PATCH, path = RegistrationRoutes.CREATE_PASSWORD_RECOVERY_CODE)
    @ResponseStatus(HttpStatus.OK)
    public void formRegisterRecovery(@RequestBody @Validated CreatePasswordRecoveryCodeCommand command){
        this.executeCommand(command);
    }

    @RequestMapping(method = RequestMethod.PATCH, path = RegistrationRoutes.PASSWORD_RESET)
    @ResponseStatus(HttpStatus.OK)
    public void passwordReset(@RequestBody @Validated UpdatePasswordWithRecoveryCodeCommand command) {
        this.executeCommand(command);
    }
}
