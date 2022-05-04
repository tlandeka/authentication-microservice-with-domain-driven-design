package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.registration.command.SendRegistrationConfirmationEmailCommand;
import com.tomo.mcauthentication.ddd.email.EmailSender;
import com.tomo.mcauthentication.ddd.port.adapter.message.email.EmailMessage;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;

import org.springframework.stereotype.Component;

@Component
public class SendRegistrationConfirmationEmailCommandHandler extends AbstractVoidyCommandHandler<SendRegistrationConfirmationEmailCommand> {

    EmailSender emailMessageSender;
    AppProperties properties;

    public SendRegistrationConfirmationEmailCommandHandler(EmailSender emailMessageSender, AppProperties properties) {
        this.emailMessageSender = emailMessageSender;
        this.properties = properties;
    }

    @Override
    protected void abstractHandle(SendRegistrationConfirmationEmailCommand aCommand) {
        //todo send email
        this.emailMessageSender.send(new EmailMessage(
                aCommand.getEmail(), ""
                , " bezze",
                "Code registration " + properties.getBaseUrl() + "/register/confirm/?confirmationCode=" + aCommand.getConfirmLink()));
    }
}
