package com.tomo.mcauthentication.infrastructure.springboot.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GUIProperties {
    String baseUrl;
    String recoveryRoute;
    String confirmationRoute;
}
