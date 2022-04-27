package com.tomo.mcauthentication.application.users.query;

import com.tomo.mcauthentication.application.contracts.security.AbstractAuthenticateQuery;
import com.tomo.mcauthentication.domain.users.UserId;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserQuery extends AbstractAuthenticateQuery {

    @NotNull
    String userId;

    public GetUserQuery(String userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return new UserId(UUID.fromString(this.userId));
    }
}
