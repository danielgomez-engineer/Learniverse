package com.danieldev.Learniverse.model;


import org.springframework.security.core.GrantedAuthority;

public enum Rol implements GrantedAuthority {
    ADMIN,
    CLIENTE;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
