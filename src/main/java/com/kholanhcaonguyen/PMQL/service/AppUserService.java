package com.kholanhcaonguyen.PMQL.service;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import com.kholanhcaonguyen.PMQL.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = repo.findByEmail(email);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        // Ensure roles are prefixed with "ROLE_"
        String[] roles = appUser.getRole() != null ? new String[] { "ROLE_" + appUser.getRole() } : new String[] { "ROLE_USER" };

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
    }
}
