package com.tomo.mcauthentication.application.users;

import com.tomo.mcauthentication.application.contracts.Result;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BaseUserDto implements Result {
    String firstName;
    String lastName;
}
