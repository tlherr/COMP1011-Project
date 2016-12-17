package com.tlherr.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractRepository {

    public abstract ResultSet load(Class toLoad) throws SQLException;
}
