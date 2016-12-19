package com.tlherr.Users;

import com.tlherr.Permissions.EmployeePermission;
import com.tlherr.Permissions.ManufacturerPermission;
import com.tlherr.Permissions.ProductPermission;

import java.security.Permission;

/**
 * This class represents an admin user with elevated permissions
 */
public class AdminUser extends BaseUser {
    public static final int USER_TYPE_ADMIN = 2;
    public static Permission employeePermission = new EmployeePermission("ELEVATED","edit,delete");
    public static Permission manufacturerPermission =new ManufacturerPermission("ELEVATED", "edit,delete");
    public static Permission productPermission = new ProductPermission("ELEVATED", "edit,delete");

    public AdminUser(String name, int id) {
        super(name, id);

        //Add elevated permissions
        this.permissions.add(employeePermission);
        this.permissions.add(manufacturerPermission);
        this.permissions.add(productPermission);
    }
}
