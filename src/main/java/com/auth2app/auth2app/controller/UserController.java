package com.auth2app.auth2app.controller;

import com.auth2app.auth2app.Data.request.AuthRequest;
import com.auth2app.auth2app.Data.request.CreateUserRequest;
import com.auth2app.auth2app.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/")
@Slf4j
public class UserController {


    private final UserServiceImpl userService;


    @PostMapping("create")
    public ResponseEntity<String> signUp(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

    @GetMapping("get")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getNames(){return "happy coding";}

    @PostMapping("log")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequest request){
        log.info("logging authentication request -----> {}", request);
        return ResponseEntity.ok(userService.authenticateUser(request));
    }
}
