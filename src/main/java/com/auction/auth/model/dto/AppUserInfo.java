package com.auction.auth.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AppUserInfo {
    private String username;
    private List<String> roles = new ArrayList<>();

    public AppUserInfo(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }
}
