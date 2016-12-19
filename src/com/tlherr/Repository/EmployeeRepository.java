package com.tlherr.Repository;

import com.tlherr.Model.Employee.*;
import com.tlherr.Service.ConnectionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeRepository extends AbstractRepository {

    private static EmployeeRepository instance = new EmployeeRepository();

    public static EmployeeRepository getInstance() {
        return instance;
    }

    private EmployeeRepository() {}

    private static ArrayList<AbstractEmployee> employees = new ArrayList<AbstractEmployee>();

    public void addEmployee(AbstractEmployee employee) {
        employees.add(employee);
    }

    public Integer getCount() {
        return employees.size();
    }

    public AbstractEmployee findByFirstName(String firstName) {
        for (AbstractEmployee employee : employees) {
            if (employee.getFirstName().equals(firstName)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public ResultSet load(Class toLoad) throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        if (toLoad == BasePlusCommissionEmployee.class) {
            //Load BasePlusCommissionEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM BasePlusCommissionEmployee");

        } else if (toLoad == CommissionSalesEmployee.class) {
            //Load CommissionSalesEmployees into result set and return it
            return statement.executeQuery("SELECT * FROM CommissionEmployee");

        } else if (toLoad == HourlyEmployee.class) {
            //Load HourlyEmployees into a result set and return it
            return statement.executeQuery("SELECT * FROM HourlyEmployee");

        } else if (toLoad == SalaryEmployee.class) {
            //Load SalaryEmployees into a result set and return it
            return statement.executeQuery("SELECT * FROM SalaryEmployee");
        } else {
            return null;
        }
    }

    /**
     * Loads all Employees from DB, have to use UNIONS because they are in different tables
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet loadAll() throws SQLException {
        Connection conn = ConnectionService.getConnection();
        Statement statement = conn.createStatement();

        return statement.executeQuery("SELECT bpc.id,bpc.firstName, 1 as emp_type from basepluscommissionemployee bpc UNION\n" +
                "SELECT c.id,c.firstName, 2 as emp_type from commissionemployee c UNION\n" +
                "SELECT h.id,h.firstName, 3 as emp_type from hourlyemployee h UNION\n" +
                "SELECT s.id,s.firstName, 4 as emp_type from salaryemployee s;");
    }
}
