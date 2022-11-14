package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.registration.command.SendRegistrationConfirmationEmailCommand;
import com.tomo.ddd.email.EmailSender;
import com.tomo.ddd.port.adapter.message.email.EmailMessage;

import org.springframework.stereotype.Component;

@Component
public class SendRegistrationConfirmationEmailCommandHandler extends AbstractVoidyCommandHandler<SendRegistrationConfirmationEmailCommand> {

    EmailSender emailMessageSender;

    public SendRegistrationConfirmationEmailCommandHandler(EmailSender emailMessageSender) {
        this.emailMessageSender = emailMessageSender;
    }

    @Override
    protected void abstractHandle(SendRegistrationConfirmationEmailCommand aCommand) {
        //todo maybe validate if exist
        this.emailMessageSender.send(new EmailMessage(
                aCommand.getEmail(), ""
                , "Confirm registration",
                "Click on this confirmation link " + aCommand.getConfirmLink() + aCommand.getConfirmationCode()));
    }
}
