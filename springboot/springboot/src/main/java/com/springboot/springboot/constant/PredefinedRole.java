package com.springboot.springboot.constant;
public enum PredefinedRole {
    USER_ROLE("BUYER"),
    ADMIN_ROLE("ADMIN");

    private final String roleName;

    PredefinedRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
