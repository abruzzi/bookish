package com.thoughtworks.bookish.model;

public class AuthenticationToken {
    private final String name;
    private final String email;

    public AuthenticationToken(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
