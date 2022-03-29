package com.tomo.mcauthentication.application.users.query;

import com.tomo.mcauthentication.application.contracts.BaseQuery;
import com.tomo.mcauthentication.domain.users.UserId;

import javax.validation.constraints.NotNull;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserQuery extends BaseQuery {

    @NotNull
    UUID userId;

    public GetUserQuery() {
        super();
    }

    public UserId getUserId() {
        return new UserId(userId);
    }
}
