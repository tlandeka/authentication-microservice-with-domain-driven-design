package com.tomo.mcauthentication.application.users.dto;

import com.tomo.mcauthentication.application.contracts.Response;
import com.tomo.mcauthentication.domain.users.UserId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseUserDto implements Response {
    private String userId;
    String firstName;
    String lastName;

    public void setUserId(UserId userId) {
        this.userId = userId.id().toString();
    }
}
