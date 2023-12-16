package com.auth2app.auth2app.config;

import com.auth2app.auth2app.models.PrincipalUser;
import com.auth2app.auth2app.repository.PrincipalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PrincipalUserUserDetailsService implements UserDetailsService {

//    @Autowired
    private final   PrincipalUserRepository repository;

    @Autowired
    public PrincipalUserUserDetailsService(PrincipalUserRepository repo){
        this.repository = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PrincipalUser> user = repository.findByEmail(username);

       return user.map(PrincipalUserUserDetails :: new)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with email " + username + " cannot be found"));
    }
}
