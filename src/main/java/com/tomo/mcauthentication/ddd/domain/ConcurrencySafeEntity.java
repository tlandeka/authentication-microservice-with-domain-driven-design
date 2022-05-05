package com.tomo.mcauthentication.ddd.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class ConcurrencySafeEntity extends RootEntity {

    private static final long serialVersionUID = 1L;

    @Version
    private int concurrencyVersion;

    protected ConcurrencySafeEntity() {
        super();
    }

}
