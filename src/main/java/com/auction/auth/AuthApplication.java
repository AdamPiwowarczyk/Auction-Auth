package com.auction.auth;

import com.auction.auth.model.ERole;
import com.auction.auth.model.Role;
import com.auction.auth.repository.RoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public InitializingBean loadData(RoleRepository roleRepository) {
        return () -> {
            if (!roleRepository.existsByRole(ERole.ROLE_ADMIN)) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN));
            }
            if (!roleRepository.existsByRole(ERole.ROLE_USER)) {
                roleRepository.save(new Role(ERole.ROLE_USER));
            }
        };
    }

}
