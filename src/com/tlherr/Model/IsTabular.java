package com.tlherr.Model;

/**
 * Interface that ensures methods needed to load data into EmployeeTableModel exist.
 * Need to be able to get the number of Rows and individual properties as string values
 */
public interface IsTabular {

    public Integer getRowCount();
    public String getProp(int index);
}
