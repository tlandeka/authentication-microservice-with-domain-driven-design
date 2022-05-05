package com.tomo.mcauthentication.application.registration;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.registration.command.SendRegistrationConfirmationEmailCommand;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.domain.registration.events.UserRegistrationRequested;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NewUserRegisteredEventProcessor {

    private McAuthenticationModule authenticationModule;

    /**
     * In order to not mix business logic with plugins like a GUI..
     * baseUrl + URI + queryString eg. localhost/register/confirm/?confirmationCode=
     */
    private String confirmationLink;

    public NewUserRegisteredEventProcessor(McAuthenticationModule authenticationModule, String confirmationLink) {
        this.authenticationModule = authenticationModule;
        this.confirmationLink = confirmationLink;
    }

    @Before(value = "execution(public * com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler.*(..)) && target(com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler))")
    public void listen() {
        DomainEventPublisher
            .instance()
            .subscribe(new DomainEventSubscriber<UserRegistrationRequested>() {

                public void handleEvent(UserRegistrationRequested aDomainEvent) {
                    authenticationModule.executeCommand(new SendRegistrationConfirmationEmailCommand(
                            aDomainEvent.getEmail(),
                            confirmationLink,
                            aDomainEvent.getConfirmationCode()));
                }

                public Class<UserRegistrationRequested> subscribedToEventType() {
                    return UserRegistrationRequested.class; // all domain events
                }
            });
    }
}
