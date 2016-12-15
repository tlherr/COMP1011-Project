package com.tlherr.Model.Employee;

import com.tlherr.Repository.EmployeeRepository;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Accepts a result set and returns a useable JTable model to display data
 */
public class EmployeeTableModel extends DefaultTableModel {

    public EmployeeTableModel(){};

    public static DefaultTableModel build(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

        System.out.println("Loading "+columnCount+" columns from result set");

        for(int i=1; i<=columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();

        while(resultSet.next())
        {
            //This will store each row
            Vector<Object> rowVector = new Vector<Object>();

            //Loop through the result set and get each object
            for(int colIndex=1; colIndex<=columnCount; colIndex++)
            {
                rowVector.add(resultSet.getObject(colIndex));
            }
            tableData.add(rowVector);
        }

        System.out.println("Result Set Contained "+tableData.size()+" elements");

        //@TODO: need to look into how this should be handled. Closing for now to avoid flooding connection pool
        resultSet.close();

        return new DefaultTableModel(tableData, columnNames);
    }

}
