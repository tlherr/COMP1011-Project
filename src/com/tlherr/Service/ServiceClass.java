package com.tlherr.Service;

/**
 * Service class as defined by requirements.
 * Provides generic helper methods. Generates IDs for employees currently
 */
public final class ServiceClass {

    private static int count = 1;

    private ServiceClass(){};

    public static Integer getId() {
        return count++;
    }

}
