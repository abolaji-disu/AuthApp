package com.auth2app.auth2app.controller;

import com.auth2app.auth2app.Data.request.CreateUserRequest;
import com.auth2app.auth2app.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/v1/user/")
public class UserController {


    private final UserServiceImpl userService;


    @PostMapping("/signIn")
    public ResponseEntity<String> signUp(@RequestBody CreateUserRequest createUserRequest){
        return new ResponseEntity<>(userService.createUser(createUserRequest), HttpStatus.CREATED);
    }

}
