package com.auth2app.auth2app.service;

import com.auth2app.auth2app.Data.request.CreateUserRequest;

public interface UserService {

    String createUser(CreateUserRequest request);
}
