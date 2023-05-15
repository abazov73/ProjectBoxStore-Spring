package com.example.ipLab.StoreDataBase.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private final Long userID;
    private final UserRole role;

    public CustomUser(String username, String password,
                      Collection<? extends GrantedAuthority> authorities, Long userID, UserRole role) {
        super(username, password, authorities);
        this.userID = userID;
        this.role = role;
    }

    public Long getUserID() {
        return userID;
    }

    public UserRole getRole() {
        return role;
    }
}
