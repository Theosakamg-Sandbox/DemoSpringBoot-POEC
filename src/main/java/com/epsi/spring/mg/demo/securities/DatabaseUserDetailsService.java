package com.epsi.spring.mg.demo.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.epsi.spring.mg.demo.entities.User;
import com.epsi.spring.mg.demo.repositories.UserRepository;

public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = this.userRepo
                            .findByEmail(username)
                            .orElseThrow( ()-> new UsernameNotFoundException(
                                String.format("USER_NOT_FOUND", username)
                            ));

        return new AppUserDetails(user);
    }

}
