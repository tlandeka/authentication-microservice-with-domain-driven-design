package com.tomo.mcauthentication.application.users;

import com.tomo.mcauthentication.application.BaseMapper;
import com.tomo.mcauthentication.application.configuration.QueryHandler;
import com.tomo.mcauthentication.application.users.dto.BaseUserDto;
import com.tomo.mcauthentication.application.users.query.GetUserQuery;
import com.tomo.mcauthentication.domain.users.User;
import com.tomo.mcauthentication.domain.users.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GetUserQueryHandler extends BaseMapper implements QueryHandler<GetUserQuery, BaseUserDto> {

    private UserRepository userRepository;

    public GetUserQueryHandler(
            UserRepository userRepository,
            ModelMapper modelMapper) {
        super(modelMapper);
        this.userRepository = userRepository;
    }

    @Override
    public BaseUserDto handle(GetUserQuery query) {
        User user = userRepository.findById(query.getUserId());

        if (user == null) {
            throw new IllegalStateException(String.format("User with id %s doesn't exists.", query.getUserId().toString()));
        }

        return toDto(user);
    }

    private BaseUserDto toDto(User user) {
        return modelMapper.map(user, BaseUserDto.class);
    }
}
