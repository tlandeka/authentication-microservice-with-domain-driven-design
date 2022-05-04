package com.tomo.mcauthentication.ddd.port.adapter.message.email;

import com.tomo.mcauthentication.ddd.email.Message;

import java.io.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailMessage implements Message {
    String to;
    String from;
    String subject;
    String body;
    File attachment;

    public EmailMessage(String to, String from, String subject, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }
}
