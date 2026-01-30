package com.first.springboot.firstwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {

    /*
    For every input (like "1234"),

    it calls your method passwordEncoder() (the one defined as a Spring Bean),

    which returns a BCryptPasswordEncoder object,

    and then .encode(input) hashes the password using BCrypt.
     */

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("Aryan", "1234");
        UserDetails userDetails2 = createNewUser("Rip", "asdf1234");

        return new InMemoryUserDetailsManager(userDetails1, userDetails2); //Stored in-memory in hashed form
    }

    private UserDetails createNewUser(String username, String password) {
        //Java functional interface : Take a raw password → return an encoded password
        final Function<String, String> encodePassword =
                raw -> passwordEncoder().encode(raw);


        return User.builder() //User.builder() method has a special overload that allows you to pass a password encoding function
                .passwordEncoder(encodePassword) //you’re telling the builder: “Whenever .password(...) is called, automatically encode it using encodePassword function”
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

//        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        //Implementation of encodePassword(interface) which uses BCrypt strong hashing function
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
                //.requestMatchers(HttpMethod.GET ,"/public/**").permitAll()       // accessible to everyone without login
                //.requestMatchers("/admin/**").hasRole("ADMIN")   // only for ADMIN
                .anyRequest().authenticated()
        )

//      .httpBasic(Customizer.withDefaults());        for pop up login

        .formLogin(Customizer.withDefaults())

        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

}
