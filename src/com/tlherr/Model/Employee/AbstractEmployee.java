package com.tlherr.Model.Employee;

import com.tlherr.Model.IsTabular;
import com.tlherr.Service.ServiceClass;

import java.util.Date;

public abstract class AbstractEmployee implements IsTabular {

    private String firstName, lastName, position, department,
            email, phoneNumber, address, gender;
    private int idNumber, vacationsDays, socialInsuranceNumber;
    private Date dateHired, dateOfBirth;

    protected AbstractEmployee() {
        this.idNumber = ServiceClass.getId();
    }

    protected AbstractEmployee(String firstName, String lastName, String position, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.department = department;
        this.idNumber = ServiceClass.getId();
    }

    protected AbstractEmployee(String firstName, String lastName, String position, String department, Integer socialInsuranceNumber,
                    String email, String phoneNumber, String address, String gender, int vacationsDays, Date dateHired, Date dateOfBirth) {
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

    public abstract float calculatePay();

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
