package com.tlherr.Repository;

import com.tlherr.Model.Manufacturer;
import com.tlherr.Service.ConnectionService;

import java.sql.*;

/**
 * Responsible for storing and accessing manufacturers as they as saved/edited/removed
 */
public class ManufacturerRepository extends AbstractRepository {

    private static ManufacturerRepository instance = new ManufacturerRepository();

    public static ManufacturerRepository getInstance() {
        return instance;
    }

    private ManufacturerRepository() {
    }

    ;

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if (toLoad == Manufacturer.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM Manufacturers");
        } else {
            return null;
        }
    }

    public ResultSet loadById(int id) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        PreparedStatement statement = conn.prepareStatement("SELECT * FROM Manufacturer WHERE id=?");
        statement.setInt(1, id);

        return statement.executeQuery();
    }


}
