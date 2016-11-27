package com.tlherr.Model.Employee;

import com.tlherr.Model.IsTabular;
import com.tlherr.Service.ServiceClass;

import java.util.Date;

/**
 * This class contains base elements and functionality for an employee. All employee types inherit from this class
 *
 * This class implements IsTablular interface so all child classes will have methods needed to be loaded into JTablel
 * model
 */
public abstract class AbstractEmployee implements IsTabular {

    private String type;
    private String firstName, lastName, position, department,
            email, phoneNumber, address, gender;
    private int idNumber, vacationsDays, socialInsuranceNumber;
    private Date dateHired, dateOfBirth;

    protected AbstractEmployee() {
        this.idNumber = ServiceClass.getId();
    }

    protected AbstractEmployee(String firstName, String lastName, String position, String department) {
        this.type = this.getClass().getSimpleName();
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.idNumber = ServiceClass.getId();
    }

    protected AbstractEmployee(String firstName, String lastName, String position, String department,
                               Integer socialInsuranceNumber, String email, String phoneNumber, String address,
                               String gender, int vacationsDays, Date dateHired, Date dateOfBirth) {
        this.type = this.getClass().getSimpleName();
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.socialInsuranceNumber = socialInsuranceNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.gender = gender;
        this.vacationsDays = vacationsDays;
        this.dateHired = dateHired;
        this.dateOfBirth = dateOfBirth;

        this.idNumber = ServiceClass.getId();
    }

    @Override
    public String toString() {
        return String.format("Name: %1$s,%2$s Id: %3$d Department: %4$s Position: %5$s",
                this.getFirstName(), this.getLastName(), this.getIdNumber(), this.getDepartment(), this.getPosition());
    }

    /**
     * This method is used by the table model to get values to be displayed in the table
     *
     * @param index Integer requested value to get (we map these thus the switch statement)
     * @return String an employee property
     */
    public String getProp(int index) {
        switch(index) {
            case 0:
                return this.type;
            case 1:
                return String.valueOf(this.idNumber);
            case 2:
                return this.firstName;
            case 3:
                return this.lastName;
            case 4:
                return this.position;
            case 5:
                return this.department;

        }

        return "Invalid Index";
    }

    /**
     * Calculate the amount the employee should be paid. Changes based on employee type
     * @return Float amount of money the employee should be paid
     */
    public abstract float calculatePay();

    /**
     * Getters/Setters
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getSocialInsuranceNumber() {
        return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(Integer socialInsuranceNumber) {
        this.socialInsuranceNumber = socialInsuranceNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public int getVacationsDays() {
        return vacationsDays;
    }

    public void setVacationsDays(int vacationsDays) {
        this.vacationsDays = vacationsDays;
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) {
        this.dateHired = dateHired;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
