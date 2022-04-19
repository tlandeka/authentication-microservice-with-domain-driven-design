package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.recovery.command.UpdatePasswordWithRecoveryCodeCommand;
import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
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
