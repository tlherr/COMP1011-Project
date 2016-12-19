package com.tlherr.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repository classes exist to load information from the Database
 * They all have at least a load method that accepts a class (usually a model class) that can tell them exactly what to
 * load
 */
public abstract class AbstractRepository {

    public abstract ResultSet load(Class toLoad) throws SQLException;
}
