package com.FinC.models.enums;

public enum UserRole {

    ADMIN("admin"),
    BASIC("basic");

    private String role;


    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}