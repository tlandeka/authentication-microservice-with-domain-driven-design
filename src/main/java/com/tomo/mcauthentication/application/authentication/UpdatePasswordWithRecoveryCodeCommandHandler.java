package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.authentication.command.UpdatePasswordWithRecoveryCodeCommand;
import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.authentication.dto.SessionDto;
import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class UpdatePasswordWithRecoveryCodeCommandHandler extends AbstractVoidyCommandHandler<UpdatePasswordWithRecoveryCodeCommand> {

    EmailAuthenticationService emailAuthenticationService;

    public UpdatePasswordWithRecoveryCodeCommandHandler(EmailAuthenticationService emailAuthenticationService) {
        this.emailAuthenticationService = emailAuthenticationService;
    }

    @Override
    protected void abstractHandle(UpdatePasswordWithRecoveryCodeCommand aCommand) {
        emailAuthenticationService.recoverPasswordWithRecoveryCode(
                aCommand.getRecoveryCode(),
                aCommand.getNewPassword(),
                aCommand.getNewPasswordRepeated());
    }
}
