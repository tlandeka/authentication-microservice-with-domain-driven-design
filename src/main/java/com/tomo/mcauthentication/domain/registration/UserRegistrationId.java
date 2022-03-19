package com.tomo.mcauthentication.domain.registration;

import com.tomo.mcauthentication.ddd.domain.AbstractId;

import javax.persistence.Embeddable;
import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationId extends AbstractId {

    public UserRegistrationId(UUID anId) {
        super(anId);
    }

    @Override
    protected int hashPrimeValue() {
        return 0;
    }

    @Override
    protected int hashOddValue() {
        return 0;
    }
}
