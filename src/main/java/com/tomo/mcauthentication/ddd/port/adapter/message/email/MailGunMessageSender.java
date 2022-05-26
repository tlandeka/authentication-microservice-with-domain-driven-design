package com.tomo.mcauthentication.ddd.port.adapter.message.email;

import com.tomo.mcauthentication.ddd.email.EmailSender;

import net.sargue.mailgun.Configuration;
import net.sargue.mailgun.Mail;
import net.sargue.mailgun.MailBuilder;

public class MailGunMessageSender implements EmailSender<EmailMessage> {

    Configuration configuration;

    public MailGunMessageSender(Configuration mailGunConfiguration) {
        this.configuration = mailGunConfiguration;
    }

    @Override
    public void send(EmailMessage message) {
        MailBuilder mailBuilder = Mail.using(configuration);
        mailBuilder.to(message.getTo())
                .subject(message.getSubject())
                .text(message.getBody());

        if (message.getAttachment() != null) {
            mailBuilder.multipart()
                    .attachment(message.getAttachment());
        }

        mailBuilder.build()
                .send();
    }
}
