package com.clinic.appointment.dao;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {

    private final static List<UserDetails> PERMISSIONS_USER = Arrays.asList(
            new User(
                    "a.elsaid@smartcardapplicaton.com",
                    "password123",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ), new User(
                    "abdelminim.zahran@gmail.com",
                    "01060708952",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            ), new User(
                    "a.test@gmail.com",
                    "P@ssw0rd",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );
    public UserDetails findUserByEmail(String email){
        return PERMISSIONS_USER
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(" No such user " + email));
    }
}
