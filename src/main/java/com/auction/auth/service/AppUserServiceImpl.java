package com.auction.auth.service;

import com.auction.auth.model.AppUser;
import com.auction.auth.model.ERole;
import com.auction.auth.model.Role;
import com.auction.auth.model.dto.AppUserDto;
import com.auction.auth.repository.AppUserRepository;
import com.auction.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public AppUser createUser(AppUserDto appUserDto) {
        AppUser user = convertAppUserDtoIntoAppUser(appUserDto);
        return appUserRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
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
