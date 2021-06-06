package com.auction.auth.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AppUserDto {
    @NotBlank
    private String username;
    private String password;
}
