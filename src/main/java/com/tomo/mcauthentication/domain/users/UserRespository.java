package com.tomo.mcauthentication.domain.users;

public interface UserRespository {
    User save (User anUser);
    UserId nextIdentity();
    User findByEmail(String anEmail);
}
