package com.tomo.mcauthentication.ddd.domain;

import java.time.LocalDateTime;

public interface Timestampable {

    LocalDateTime getCreated();

    void setCreated(LocalDateTime created);

    LocalDateTime getModified();

    void setModified(LocalDateTime modified);
}

