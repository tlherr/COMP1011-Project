package com.tlherr.Service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceClassTest {

    @Test
    public void testGetId() throws Exception {
        Integer id = ServiceClass.getId();
        id++;
        assertEquals(id, ServiceClass.getId());
    }
}