package com.tomo.mcauthentication.infrastructure.springboot.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MessageProperties {

    private Email email = new Email();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Email {
        MailGun mailGun = new MailGun();
        String fakeEmail = "tomo.landeka02@gmail.com";

        @Getter
        @Setter
        @NoArgsConstructor
        public static class MailGun {
            String domains;
            String apiUrl;
            String apiKey;
            From from;

            @Getter
            @Setter
            @NoArgsConstructor
            public static class From {
                String name;
                String email;
            }
        }
    }
}
