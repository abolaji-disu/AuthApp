package com.auth2app.auth2app.controller;

import com.auth2app.auth2app.Data.request.CreateUserRequest;
import com.auth2app.auth2app.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/basic/auth/")
public class UserController {


    private final UserServiceImpl userService;


    @PostMapping("create")
    public ResponseEntity<String> signUp(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getNames(){return "happy coding";}
}
