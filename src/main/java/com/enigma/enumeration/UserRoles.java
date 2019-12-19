package com.enigma.enumeration;

import com.enigma.constans.RoleConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum UserRoles {
    ADMINISTRATOR(RoleConstants.ROLE_ADMINISTRATOR),
    OWNER(RoleConstants.ROLE_OWNER),
    OPERATOR(RoleConstants.ROLE_OPERATOR),
    CUSTOMER(RoleConstants.ROLE_CUSTOMER);

    private String label;

    public static UserRoles getUserRoleByLabel(String label) {
        for (UserRoles role: UserRoles.values()) {
            if (role.getLabel().equals(label)) return role;
        }
        return null;
    }
}
