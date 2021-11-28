package com.auction.auth.service;

import com.auction.auth.model.AppUser;
import com.auction.auth.model.dto.AppUserDto;

public interface AppUserService {
    AppUser createUser(AppUserDto appUserDto);

    boolean existsByUsername(String username);
}
