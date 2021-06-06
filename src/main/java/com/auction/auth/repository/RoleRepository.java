package com.auction.auth.repository;

import com.auction.auth.model.ERole;
import com.auction.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRole(ERole role);
    Role findByRole(ERole role);
}
