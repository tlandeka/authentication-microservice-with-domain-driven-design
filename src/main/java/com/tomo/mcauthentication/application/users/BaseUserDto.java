package com.tomo.mcauthentication.application.users;

import com.tomo.mcauthentication.application.contracts.Result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseUserDto implements Result {
    String firstName;
    String lastName;
}
