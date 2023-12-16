package com.auth2app.auth2app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String roles;

}
