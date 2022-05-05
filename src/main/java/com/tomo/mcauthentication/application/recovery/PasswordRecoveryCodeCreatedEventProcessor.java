package com.tomo.mcauthentication.application.recovery;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.recovery.command.SendPasswordRecoveryEmailCommand;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.domain.registration.events.PasswordRecoveryCodeCreated;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PasswordRecoveryCodeCreatedEventProcessor {

    private McAuthenticationModule module;

    /**
     * In order to not mix business logic with plugins like a GUI..
     * baseUrl + URI + queryString eg. localhost/reset-passwrod/?recoveryCode=
     */
    private String recoveryLink;

    public PasswordRecoveryCodeCreatedEventProcessor(McAuthenticationModule module, String recoveryLink) {
        this.module = module;
        this.recoveryLink = recoveryLink;
    }

    /**
     * This factory method is provided in the case where
     * Spring AOP wiring is not desired.
     */
    public static void register() {
        (new PasswordRecoveryCodeCreatedEventProcessor()).listen();
    }

    /**
     * Constructs my default state.
     */
    public PasswordRecoveryCodeCreatedEventProcessor() {
        super();
    }

    @Before("execution(* com.tomo.mcauthentication.application.*.*(..))")
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
                    return PasswordRecoveryCodeCreated.class; // all domain events
                }
            });
    }
}
