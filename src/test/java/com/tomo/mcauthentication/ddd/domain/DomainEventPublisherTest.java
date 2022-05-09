package com.tomo.mcauthentication.ddd.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DomainEventPublisherTest {

    private boolean eventHandled1 = false;
    private boolean eventHandled2 = false;

    @Test
    public void testDomainEventPublisherPublished() {
        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<BaseDomainEvent>() {
            @Override
            public void handleEvent(BaseDomainEvent aDomainEvent) {
                eventHandled1 = true;
            }

            @Override
            public Class<BaseDomainEvent> subscribedToEventType() {
                return BaseDomainEvent.class;
            }
        });

        DomainEventPublisher.instance().publish(new BaseDomainEvent());
        assertTrue(eventHandled1);
    }

    @Test
    public void DomainEventPublisherBlocker() {
        eventHandled1 = false;

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<BaseDomainEvent>() {
            @Override public void handleEvent(BaseDomainEvent aDomainEvent) {
                eventHandled1 = true;
                DomainEventPublisher.instance().publish(new TestableDomainEvent());
            }

            @Override public Class<BaseDomainEvent> subscribedToEventType() {
                return BaseDomainEvent.class;
            }
        });

        DomainEventPublisher.instance().subscribe(new DomainEventSubscriber<TestableDomainEvent>() {
            @Override public void handleEvent(TestableDomainEvent aDomainEvent) {
                eventHandled2 = true;
            }

            @Override public Class<TestableDomainEvent> subscribedToEventType() {
                return TestableDomainEvent.class;
            }
        });

        DomainEventPublisher.instance().publish(new BaseDomainEvent());
        assertTrue(eventHandled1);
        assertFalse(eventHandled2);
    }

}
