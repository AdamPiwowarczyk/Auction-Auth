package com.auction.auth.service;

import com.auction.auth.model.AppUser;
import com.auction.auth.model.ERole;
import com.auction.auth.model.Role;
import com.auction.auth.model.dto.AppUserDto;
import com.auction.auth.model.dto.AppUserInfo;
import com.auction.auth.repository.AppUserRepository;
import com.auction.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public List<AppUserInfo> getUsers() {
        return appUserRepository.findAll().stream()
                .map(this::convertAppUserIntoAppUserInfo)
                .collect(Collectors.toList());
    }

    @Override
    public Long createUser(AppUserDto appUserDto) {
        AppUser user = convertAppUserDtoIntoAppUser(appUserDto);
        return appUserRepository.save(user).getId();
    }

    private AppUserInfo convertAppUserIntoAppUserInfo(AppUser user) {
        return new AppUserInfo(user.getUsername(),
                user.getRoles().stream()
                        .map(role -> role.getRole().name())
                        .collect(Collectors.toList()));
    }

    private AppUser convertAppUserDtoIntoAppUser(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDto.getUsername());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        Role role = roleRepository.findByRole(ERole.ROLE_USER);
        appUser.getRoles().add(role);
        return appUser;
    }
}
