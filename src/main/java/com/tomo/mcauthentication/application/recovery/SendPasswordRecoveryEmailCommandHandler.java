package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.recovery.command.SendPasswordRecoveryEmailCommand;
import com.tomo.mcauthentication.application.registration.command.SendRegistrationConfirmationEmailCommand;
import com.tomo.mcauthentication.ddd.email.EmailSender;
import com.tomo.mcauthentication.ddd.port.adapter.message.email.EmailMessage;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;

import org.springframework.stereotype.Component;

@Component
public class SendPasswordRecoveryEmailCommandHandler extends AbstractVoidyCommandHandler<SendPasswordRecoveryEmailCommand> {

    EmailSender emailMessageSender;

    public SendPasswordRecoveryEmailCommandHandler(EmailSender emailMessageSender) {
        this.emailMessageSender = emailMessageSender;
    }

    @Override
    protected void abstractHandle(SendPasswordRecoveryEmailCommand aCommand) {
        //todo maybe validate if exist
        this.emailMessageSender.send(new EmailMessage(
                aCommand.getEmail(), ""
                , " Recovery code",
                "To reset your password, visit the following link: " + aCommand.getRecoveryLink() + aCommand.getRecoveryCode()));
    }
}
