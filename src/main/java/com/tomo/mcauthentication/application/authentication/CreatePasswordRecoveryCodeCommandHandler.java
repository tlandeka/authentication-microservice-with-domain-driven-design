package com.tomo.mcauthentication.application.authentication;

import com.tomo.mcauthentication.application.authentication.command.CreatePasswordRecoveryCodeCommand;
import com.tomo.mcauthentication.application.authentication.dto.RecoveryPasswordDto;
import com.tomo.mcauthentication.application.configuration.CommandHandler;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;

import org.springframework.stereotype.Service;

@Service
public class CreatePasswordRecoveryCodeCommandHandler implements CommandHandler<CreatePasswordRecoveryCodeCommand, RecoveryPasswordDto> {

    EmailAuthenticationService emailAuthenticationService;

    public CreatePasswordRecoveryCodeCommandHandler(EmailAuthenticationService emailAuthenticationService) {
        this.emailAuthenticationService = emailAuthenticationService;
    }

    @Override
    public RecoveryPasswordDto handle(CreatePasswordRecoveryCodeCommand aCommand) {
        return new RecoveryPasswordDto(
                emailAuthenticationService
                        .createPasswordRecoveryCode(aCommand.getEmail())
        );
    }
}
