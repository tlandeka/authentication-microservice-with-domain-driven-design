package com.tomo.mcauthentication.domain.session;

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
public class SessionId extends AbstractId {

    public SessionId(UUID id) {
        super(id);
    }

    @Override
    protected int hashOddValue() {
        return 5785;
    }

    @Override
    protected int hashPrimeValue() {
        return 31;
    }
}

