package com.auth2app.auth2app.repository;

import com.auth2app.auth2app.models.PrincipalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PrincipalUserRepository extends JpaRepository<PrincipalUser, String> {

    Optional<PrincipalUser> findByEmail(String s);
}
