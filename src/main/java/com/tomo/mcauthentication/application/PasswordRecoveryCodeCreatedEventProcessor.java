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
import com.tomo.mcauthentication.application.recovery.command.SendPasswordRecoveryEmailCommand;
import com.tomo.mcauthentication.ddd.domain.DomainEvent;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.ddd.event.EventStore;
import com.tomo.mcauthentication.domain.registration.events.PasswordRecoveryCodeCreated;
import com.tomo.mcauthentication.infrastructure.springboot.configuration.AppProperties;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
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
