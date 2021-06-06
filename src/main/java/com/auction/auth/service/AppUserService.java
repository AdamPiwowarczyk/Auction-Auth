package com.auction.auth.service;

import com.auction.auth.model.dto.AppUserDto;
import com.auction.auth.model.dto.AppUserInfo;

import java.util.List;

public interface AppUserService {
    List<AppUserInfo> getUsers();

    Long createUser(AppUserDto appUserDto);
}
