package com.tomo.mcauthentication.ddd.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Clock;
import java.time.LocalDateTime;

public class TimestampableListener {

    // for testing
    private static Clock clock = Clock.systemDefaultZone();

    @PrePersist
    public void generateCreated(Timestampable object) {
        object.setCreated(LocalDateTime.now(clock));
    }

    @PreUpdate
    public void generateModified(Timestampable object) {
        object.setModified(LocalDateTime.now(clock));
    }
}
