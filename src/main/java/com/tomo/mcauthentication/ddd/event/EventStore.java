package com.tomo.mcauthentication.ddd.event;

import com.tomo.mcauthentication.ddd.domain.DomainEvent;

import java.util.List;

public interface EventStore {

    List<StoredEvent> allStoredEventsBetween(Long aLowStoredEventId, Long aHighStoredEventId);

    List<StoredEvent> allStoredEventsSince(Long aStoredEventId);

    StoredEvent append(DomainEvent aDomainEvent);

    void close();

    long countStoredEvents();
}
