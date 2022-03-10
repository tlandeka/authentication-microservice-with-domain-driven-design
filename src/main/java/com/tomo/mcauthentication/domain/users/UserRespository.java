package com.tomo.mcauthentication.domain.users;

import com.tomo.mcauthentication.ddd.domain.BaseRepository;

public interface UserRespository extends BaseRepository<User, UserId> {
    UserId nextIdentity();
    User findByEmail(String anEmail);
}
