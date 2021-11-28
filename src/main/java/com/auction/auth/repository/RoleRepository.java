package com.auction.auth.repository;

import com.auction.auth.model.ERole;
import com.auction.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(ERole role);

    boolean existsByRole(ERole role);//to jest tylko dla InitializingBean, który wprowadza 2 role do bd podczas startu

}
