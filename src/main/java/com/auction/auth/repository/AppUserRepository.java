package com.auction.auth.repository;

import com.auction.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);

    boolean existsByUsername(String username);
}
