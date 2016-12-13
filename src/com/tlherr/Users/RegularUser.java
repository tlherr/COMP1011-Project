package com.tlherr.Users;

/**
 * This class represents a regular user
 */
public class RegularUser extends BaseUser {
    public static final int USER_TYPE_BASIC = 1;
    public RegularUser(String name) {
        super(name);
    }
}
