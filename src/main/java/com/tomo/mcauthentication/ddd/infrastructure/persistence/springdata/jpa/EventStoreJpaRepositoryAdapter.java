package com.tomo.mcauthentication.ddd.infrastructure.persistence.springdata.jpa;

import com.tomo.mcauthentication.ddd.domain.DomainEvent;
import com.tomo.mcauthentication.ddd.event.EventSerializer;
import com.tomo.mcauthentication.ddd.event.EventStore;
import com.tomo.mcauthentication.ddd.event.StoredEvent;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventStoreJpaRepositoryAdapter implements EventStore {

    StoredEventJpaRepository jpaRepository;

    public EventStoreJpaRepositoryAdapter(StoredEventJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(Long aLowStoredEventId, Long aHighStoredEventId) {
        return jpaRepository.findAllByEventIdLessThanEqualAndEventIdGreaterThanEqual(aLowStoredEventId, aHighStoredEventId);
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(Long aStoredEventId) {
        return jpaRepository.findAllByEventIdGreaterThanEqual(aStoredEventId);
    }

    @Override
    public StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
                              EventSerializer.instance().serialize(aDomainEvent);

        StoredEvent storedEvent =
                new StoredEvent(
                        aDomainEvent.getClass().getName(),
                        aDomainEvent.occurredOn(),
                        eventSerialization);

        jpaRepository.save(storedEvent);

        return storedEvent;
    }

    @Override
    public void close() {

    }

    @Override
    public long countStoredEvents() {
        return jpaRepository.count();
    }
}
