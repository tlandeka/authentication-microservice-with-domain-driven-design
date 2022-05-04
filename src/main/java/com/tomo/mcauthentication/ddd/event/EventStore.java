package com.tomo.mcauthentication.ddd.event;

import com.tomo.mcauthentication.ddd.domain.DomainEvent;

import java.util.List;

public interface EventStore {

    public List<StoredEvent> allStoredEventsBetween(long aLowStoredEventId, long aHighStoredEventId);

    public List<StoredEvent> allStoredEventsSince(long aStoredEventId);

    public StoredEvent append(DomainEvent aDomainEvent);

    public void close();

    public long countStoredEvents();
}
