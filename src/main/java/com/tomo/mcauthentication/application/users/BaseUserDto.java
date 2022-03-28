package com.tomo.mcauthentication.application.users;

import com.tomo.mcauthentication.application.contracts.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseUserDto implements Response {
    String firstName;
    String lastName;
}
