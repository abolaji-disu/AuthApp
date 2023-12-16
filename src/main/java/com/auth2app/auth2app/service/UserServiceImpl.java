package com.auth2app.auth2app.service;

import com.auth2app.auth2app.Data.request.CreateUserRequest;
import com.auth2app.auth2app.advice.UserAlreadyExistException;
import com.auth2app.auth2app.models.PrincipalUser;
import com.auth2app.auth2app.repository.PrincipalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final PrincipalUserRepository userRepository;

    @Override
    public String createUser(CreateUserRequest createUserRequest) {
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent())
            throw new UserAlreadyExistException(String.format("User with email %s already exist", createUserRequest.getEmail()));

        PrincipalUser newUser = PrincipalUser.builder()
                .email(createUserRequest.getEmail())
                .roles("ROLE_USER")
                .password(createUserRequest.getPassword())
                .firstName(createUserRequest.getFirstName())
                .lastName(createUserRequest.getLastName())
                .build();

        PrincipalUser user = userRepository.save(newUser);

        return String.format("user with email %s has been created", user.getEmail());
    }


}
