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

    public Vector<String> columnNames;
    public Vector<Vector<Object>> tableData;

    public EmployeeTableModel(){};

    public DefaultTableModel build(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();

        columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();

        for(int i=1; i<=columnCount; i++) {
            columnNames.add(metaData.getColumnName(i));
        }

        tableData = new Vector<Vector<Object>>();

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

        return new DefaultTableModel(tableData, columnNames);
    }

}
