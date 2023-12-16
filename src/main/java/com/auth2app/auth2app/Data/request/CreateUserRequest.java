package com.auth2app.auth2app.Data.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    @NotBlank(message = "field cannot be null")
    private String firstName;

    @NotBlank(message = "field cannot be null")
    private String lastName;

    @NotBlank(message = "field cannot be null")
    private String email;

    @NotBlank(message = "field cannot be null")
    private String password;


}
