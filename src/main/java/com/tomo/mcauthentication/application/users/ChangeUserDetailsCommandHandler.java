package com.tomo.mcauthentication.application.users;

import com.tomo.mcauthentication.application.configuration.AbstractVoidyCommandHandler;
import com.tomo.mcauthentication.application.users.command.ChangeUserDetailsCommand;
import com.tomo.mcauthentication.domain.registration.EmailAuthenticationService;
import com.tomo.mcauthentication.domain.session.TokenProvider;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserId;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChangeUserDetailsCommandHandler extends AbstractVoidyCommandHandler<ChangeUserDetailsCommand> {

    EmailAuthenticationService emailAuthenticationService;
    TokenProvider tokenProvider;
    UserRepository userRepository;

    @Override
    public void abstractHandle(ChangeUserDetailsCommand aCommand) {
        UserId userId = tokenProvider.getUserIdFromToken(aCommand.getAccessToken());

        if (!aCommand.getUserId().equals(userId.id())) {
            throw new IllegalArgumentException(String.format("Forbidden access. User with ID %s cannot change user details for user with ID: %s", userId.id(), aCommand.getUserId()));
        }

        User user = userRepository.findById(new UserId(aCommand.getUserId()));
        user.updateDetails(aCommand.getFirstName(), aCommand.getLastName());

        userRepository.save(user);
    }
}
