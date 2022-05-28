package com.tomo.mcauthentication.ddd.domain;

public interface DomainEventSubscriber<T> {

    void handleEvent(final T aDomainEvent);

    Class<T> subscribedToEventType();
}
