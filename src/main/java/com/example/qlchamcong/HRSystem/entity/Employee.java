package com.example.qlchamcong.HRSystem.entity;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String name;
    private RoleEmployee role;
    private Department department;
    private Date dateStartWork;

    public Employee(int employeeId, String name, RoleEmployee role, Department department,Date dateStartWork) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.department = department;
        this.dateStartWork = dateStartWork;
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public RoleEmployee getRole() {
        return role;
    }

    public Department getDepartment() {
        return department;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }
}
