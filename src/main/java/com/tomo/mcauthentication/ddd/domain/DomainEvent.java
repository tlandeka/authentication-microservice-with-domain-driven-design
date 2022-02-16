package com.tomo.mcauthentication.ddd.domain;

import java.time.LocalDateTime;

public interface DomainEvent {
    int eventVersion();

    LocalDateTime occuredOn();
}
