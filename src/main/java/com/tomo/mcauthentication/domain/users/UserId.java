package com.tomo.mcauthentication.domain.users;

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
public class UserId extends AbstractId {

    public UserId(UUID id) {
        super(id);
    }

    @Override
    protected int hashOddValue() {
        return 83811;
    }

    @Override
    protected int hashPrimeValue() {
        return 263;
    }
}
