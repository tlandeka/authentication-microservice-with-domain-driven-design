package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.recovery.command.SendPasswordRecoveryEmailCommand;
import com.tomo.ddd.domain.DomainEventPublisher;
import com.tomo.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.domain.registration.events.PasswordRecoveryCodeCreated;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordRecoveryCodeCreatedEventHandler {

    private McAuthenticationModule module;

    /**
     * In order to not mix business logic with plugins like a GUI..
     * baseUrl + URI + queryString eg. localhost/reset-passwrod/?recoveryCode=
     */
    private String recoveryLink;

    public PasswordRecoveryCodeCreatedEventHandler(McAuthenticationModule authenticationModule, String recoveryLink) {
        this.module = authenticationModule;
        this.recoveryLink = recoveryLink;
    }

    @Before("execution(" +
                    "public * com.tomo.mcauthentication.application.configuration.CommandHandler.*(..)) && " +
                    "target(com.tomo.mcauthentication.application.recovery.CreatePasswordRecoveryCodeCommandHandler))")
    public void listen() {
        DomainEventPublisher
            .instance()
            .subscribe(new DomainEventSubscriber<PasswordRecoveryCodeCreated>() {

                public void handleEvent(PasswordRecoveryCodeCreated aDomainEvent) {
                    module.executeCommand(new SendPasswordRecoveryEmailCommand(
                            aDomainEvent.getEmail(),
                            aDomainEvent.getRecoveryCode(),
                            recoveryLink,
                            aDomainEvent.getRecoveryCodeExpirationDate()));
                }

                public Class<PasswordRecoveryCodeCreated> subscribedToEventType() {
                    return PasswordRecoveryCodeCreated.class;
                }
            });
    }
}
