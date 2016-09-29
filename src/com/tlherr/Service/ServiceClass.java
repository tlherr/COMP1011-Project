package com.tlherr.Service;

import java.util.UUID;

public final class ServiceClass {

    private static int count = 1;

    private ServiceClass(){};

    public static Integer getId() {
        return count++;
    }

}
