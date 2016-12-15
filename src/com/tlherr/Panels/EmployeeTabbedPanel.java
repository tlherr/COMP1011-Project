package com.tlherr.Panels;

import com.tlherr.Listener.AuthenticationListener;
import com.tlherr.Model.Employee.BasePlusCommissionEmployee;
import com.tlherr.Model.Employee.EmployeeTableModel;
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
        basePlusCommissionPanel.add(new JScrollPane(basePlusCommissionTable), BorderLayout.NORTH);

        commissionSalesPanel = new JPanel(new BorderLayout());
        commissionSalesTable = new JTable(new EmployeeTableModel());
        commissionSalesTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        commissionSalesTable.setFillsViewportHeight(true);
        commissionSalesPanel.add(new JScrollPane(commissionSalesTable), BorderLayout.NORTH);

        hourlyPanel = new JPanel(new BorderLayout());
        hourlyTable = new JTable(new EmployeeTableModel());
        hourlyTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        hourlyTable.setFillsViewportHeight(true);
        hourlyPanel.add(new JScrollPane(hourlyTable), BorderLayout.NORTH);

        salaryPanel = new JPanel(new BorderLayout());
        salaryTable = new JTable(new EmployeeTableModel());
        salaryTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        salaryTable.setFillsViewportHeight(true);
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
                try {
                    ResultSet rs = EmployeeRepository.loadResultSetOrNull(BasePlusCommissionEmployee.class);
                    if(rs!=null) {
                        basePlusCommissionTable.setModel(EmployeeTableModel.build(rs));
                    }
                } catch (SQLException e1) {
                    //@TODO: Handle with loggin class as stated in requirements
                    e1.printStackTrace();
                }
            }

            @Override
            public void loggedOut(ActionEvent e) {

            }
        });


        add(employeeContainerPane, BorderLayout.NORTH);
    }




}
