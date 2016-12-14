package com.tlherr.Permissions;

import java.security.BasicPermission;


public class EmployeePermission extends BasicPermission {
    /**
     * Creates a new BasicPermission object with the specified name.
     * The name is the symbolic name of the BasicPermission, and the
     * actions String is currently unused.
     *
     * @param name    the name of the BasicPermission.
     * @param actions ignored.
     * @throws NullPointerException     if {@code name} is {@code null}.
     * @throws IllegalArgumentException if {@code name} is empty.
     */
    public EmployeePermission(String name, String actions) {
        super(name, actions);
    }
}
