package com.tlherr.Users;

import com.tlherr.Permissions.EmployeePermission;
import com.tlherr.Permissions.ManufacturerPermission;
import com.tlherr.Permissions.ProductPermission;

/**
 * This class represents an admin user with elevated permissions
 */
public class AdminUser extends BaseUser {
    public static final int USER_TYPE_ADMIN = 2;

    public AdminUser(String name, int id) {
        super(name, id);
        //Add elevated permissions
        this.permissions.add(new EmployeePermission("ELEVATED", "edit,delete"));
        this.permissions.add(new ManufacturerPermission("ELEVATED", "edit,delete"));
        this.permissions.add(new ProductPermission("ELEVATED", "edit,delete"));
    }
}
