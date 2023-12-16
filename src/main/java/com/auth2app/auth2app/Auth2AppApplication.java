package com.auth2app.auth2app;

//import com.auth2app.auth2app.config.PrincipalUserUserDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(value = {"com.auth2app.auth2app.models"})
//@ComponentScan(basePackageClasses = {PrincipalUserUserDetails.class})
public class Auth2AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(Auth2AppApplication.class, args);
    }

}
