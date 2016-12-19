package com.tlherr.Users;


import com.tlherr.Permissions.EmployeePermission;
import com.tlherr.Permissions.ManufacturerPermission;
import com.tlherr.Permissions.ProductPermission;

import java.security.Permissions;

/**
 * Generic user with all base properties
 * Using Permission Class to track permissions
 */
public abstract class BaseUser {

    protected Permissions permissions;
    protected String name;
    protected int id;

    public BaseUser(String name, int id) {
        this.name = name;
        permissions = new Permissions();
        //Establish Base Permissions
        permissions.add(new EmployeePermission("BASIC", "read,write"));
        permissions.add(new ProductPermission("BASIC", "read,write"));
        permissions.add(new ManufacturerPermission("BASIC", "read,write"));
    }

    public String getName() {
        return name;
    }

    public Permissions getPermissions() {
        return permissions;
    }
}
