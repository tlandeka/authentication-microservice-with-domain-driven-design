package com.tomo.mcauthentication.ddd.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestableDomainEvent implements DomainEvent {

    private int eventVersion;
    private LocalDateTime occuredOn;

    public TestableDomainEvent() {
        this.eventVersion = 1;
        this.occuredOn = LocalDateTime.now();
    }

    @Override
    public int eventVersion() {
        return eventVersion;
    }

    @Override
    public LocalDateTime occuredOn() {
        return occuredOn;
    }
}
