//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.tomo.mcauthentication.application;

import com.tomo.mcauthentication.application.contracts.McAuthenticationModule;
import com.tomo.mcauthentication.application.registration.command.SendRegistrationConfirmationEmailCommand;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.domain.registration.events.NewUserRegistered;

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

    /**
     * Listens for all domain events and stores them.
     */
    @Before("execution(public * com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler.*(..)) && target(com.tomo.mcauthentication.application.registration.RegisterNewUserCommandHandler))")
    public void listen() {
        int a = 5;
        DomainEventPublisher
            .instance()
            .subscribe(new DomainEventSubscriber<NewUserRegistered>() {

                public void handleEvent(NewUserRegistered aDomainEvent) {
                    authenticationModule.executeCommand(new SendRegistrationConfirmationEmailCommand(
                            aDomainEvent.getEmail(),
                            confirmationLink,
                            aDomainEvent.getConfirmationCode()));
                }

                public Class<NewUserRegistered> subscribedToEventType() {
                    return NewUserRegistered.class; // all domain events
                }
            });
    }
}
