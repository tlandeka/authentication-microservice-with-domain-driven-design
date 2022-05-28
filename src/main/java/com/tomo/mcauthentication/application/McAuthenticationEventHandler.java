package com.tomo.mcauthentication.application;

import com.tomo.mcauthentication.ddd.domain.DomainEvent;
import com.tomo.mcauthentication.ddd.domain.DomainEventPublisher;
import com.tomo.mcauthentication.ddd.domain.DomainEventSubscriber;
import com.tomo.mcauthentication.ddd.event.EventStore;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class McAuthenticationEventHandler {

    private EventStore eventStore;

    public McAuthenticationEventHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Before(value = "execution(* *(..)) && within(com.tomo.mcauthentication.application..*)")
    public void listen() {
        DomainEventPublisher
            .instance()
            .subscribe(new DomainEventSubscriber<DomainEvent>() {

                public void handleEvent(DomainEvent aDomainEvent) {
                    store(aDomainEvent);
                }

                public Class<DomainEvent> subscribedToEventType() {
                    return DomainEvent.class; // all domain events
                }
            });
    }

    /**
     * Stores aDomainEvent to the event store.
     * @param aDomainEvent the DomainEvent to store
     */
    private void store(DomainEvent aDomainEvent) {
        this.eventStore().append(aDomainEvent);
    }

    /**
     * Answers my EventStore.
     * @return EventStore
     */
    private EventStore eventStore() {
        return this.eventStore;
    }
}
