package com.auction.auth.controller;

import com.auction.auth.model.ErrorInfo;
import com.auction.auth.model.dto.AppUserDto;
import com.auction.auth.model.dto.AppUserInfo;
import com.auction.auth.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserService appUserService;
    private final AuthenticationManager authenticationManager;

    //http://localhost:8888/users/registration
    @PostMapping("/registration")
    public ResponseEntity<?> addUser(@RequestBody @Valid AppUserDto appUserDto) {
        if (appUserService.existsByUsername(appUserDto.getUsername())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorInfo("Nazwa użytkownika jest zajęta"));
        }
        return ResponseEntity.ok().body(appUserService.createUser(appUserDto));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();

        AppUserInfo appUser = new AppUserInfo(username, roles);
        return ResponseEntity.ok(appUser);
    }
}
