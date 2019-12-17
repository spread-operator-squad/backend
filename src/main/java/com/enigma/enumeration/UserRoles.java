package com.enigma.enumeration;

import com.enigma.constans.RoleConstants;

public enum UserRoles {
    ADMINISTRATOR(RoleConstants.ROLE_ADMINISTRATOR),
    OWNER(RoleConstants.ROLE_OWNER),
    OPERATOR(RoleConstants.ROLE_OPERATOR),
    CUSTOMER(RoleConstants.ROLE_CUSTOMER);

    private String label;

    UserRoles(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
