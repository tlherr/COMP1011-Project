package com.tlherr.Panels;

import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Employee.*;
import com.tlherr.Repository.EmployeeRepository;
import com.tlherr.Resources.Strings;
import com.tlherr.Service.LoginService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This panel contains a tabbed panel with one tab for each user type
 * Each panel contains a JTable
 */
public class EmployeeTabbedPanel extends JPanel {

    private JTabbedPane employeeContainerPane;

    private JPanel basePlusCommissionPanel;
    private JTable basePlusCommissionTable;

    private JPanel commissionSalesPanel;
    private JTable commissionSalesTable;

    private JPanel hourlyPanel;
    private JTable hourlyTable;

    private JPanel salaryPanel;
    private JTable salaryTable;

    public EmployeeTabbedPanel() {
        super(new BorderLayout());

        employeeContainerPane = new JTabbedPane();

        basePlusCommissionPanel = new JPanel(new BorderLayout());
        basePlusCommissionTable = new JTable();
        basePlusCommissionTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        basePlusCommissionTable.setFillsViewportHeight(true);
        basePlusCommissionTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        basePlusCommissionPanel.add(new JScrollPane(basePlusCommissionTable), BorderLayout.NORTH);

        commissionSalesPanel = new JPanel(new BorderLayout());
        commissionSalesTable = new JTable(new EmployeeTableModel());
        commissionSalesTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        commissionSalesTable.setFillsViewportHeight(true);
        commissionSalesTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        commissionSalesPanel.add(new JScrollPane(commissionSalesTable), BorderLayout.NORTH);

        hourlyPanel = new JPanel(new BorderLayout());
        hourlyTable = new JTable(new EmployeeTableModel());
        hourlyTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        hourlyTable.setFillsViewportHeight(true);
        hourlyTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        hourlyPanel.add(new JScrollPane(hourlyTable), BorderLayout.NORTH);

        salaryPanel = new JPanel(new BorderLayout());
        salaryTable = new JTable(new EmployeeTableModel());
        salaryTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        salaryTable.setFillsViewportHeight(true);
        salaryTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        salaryPanel.add(new JScrollPane(salaryTable), BorderLayout.NORTH);

        //@TODO: Each panel will need an event listener for an element being selected

        employeeContainerPane.add(Strings.BASE_PLUS_COMMISSION_EMPLOYEE_TAB, basePlusCommissionPanel);
        employeeContainerPane.add(Strings.COMMISSION_SALES_EMPLOYEE_TAB, commissionSalesPanel);
        employeeContainerPane.add(Strings.HOURLY_EMPLOYEE_TAB, hourlyPanel);
        employeeContainerPane.add(Strings.SALARY_EMPLOYEE_TAB, salaryPanel);

        LoginService.getInstance().addListener(new AuthenticationListener() {
            @Override
            public void loggedIn(ActionEvent e) {
                //When user logs in load up table data
                updateEmployeeTables();
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });


        add(employeeContainerPane, BorderLayout.NORTH);
    }

    public void updateEmployeeTables() {
        try {
            //@TODO: This is pretty dirty, should be refactored out into class methods
            ResultSet bpcRs = EmployeeRepository.loadResultSetOrNull(BasePlusCommissionEmployee.class);
            if(bpcRs!=null) {
                basePlusCommissionTable.setModel(EmployeeTableModel.build(bpcRs));
            }

            ResultSet csRs = EmployeeRepository.loadResultSetOrNull(CommissionSalesEmployee.class);
            if(csRs!=null) {
                commissionSalesTable.setModel(EmployeeTableModel.build(csRs));
            }

            ResultSet hRs = EmployeeRepository.loadResultSetOrNull(HourlyEmployee.class);
            if(hRs!=null) {
                hourlyTable.setModel(EmployeeTableModel.build(hRs));
            }

            ResultSet sRs = EmployeeRepository.loadResultSetOrNull(SalaryEmployee.class);
            if(sRs!=null) {
                salaryTable.setModel(EmployeeTableModel.build(sRs));
            }

        } catch (SQLException e1) {
            //@TODO: Handle with loggin class as stated in requirements
            e1.printStackTrace();
        }
    }



    public JTable getBasePlusCommissionTable() {
        return basePlusCommissionTable;
    }

    public JTable getCommissionSalesTable() {
        return commissionSalesTable;
    }

    public JTable getHourlyTable() {
        return hourlyTable;
    }

    public JTable getSalaryTable() {
        return salaryTable;
    }
}
