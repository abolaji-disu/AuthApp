package com.auth2app.auth2app.service;

import com.auth2app.auth2app.Data.request.AuthRequest;
import com.auth2app.auth2app.Data.request.CreateUserRequest;
import com.auth2app.auth2app.advice.UserAlreadyExistException;
import com.auth2app.auth2app.config.jwt.JwtService;
import com.auth2app.auth2app.models.PrincipalUser;
import com.auth2app.auth2app.repository.PrincipalUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService  {

    private final PrincipalUserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public String createUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent())
            throw new UserAlreadyExistException(String.format("User with email %s already exist", createUserRequest.getEmail()));

        PrincipalUser newUser = PrincipalUser.builder()
                .email(createUserRequest.getEmail())
                .roles("ROLE_USER")
                .password(encoder.encode(createUserRequest.getPassword()))
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .build();

        PrincipalUser user = userRepository.save(newUser);

        return String.format("user with email %s has been created", user.getEmail());
    }

    @Override
    public String authenticateUser(AuthRequest req) {

        log.info("logging before authentication -------> {}", req);

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));

        log.info("logging after authentication -------> {}", authentication);

        if (authentication.isAuthenticated())
            return jwtService.generateToken(req.getEmail());


        return "User cannot be authenticated";
    }


}
