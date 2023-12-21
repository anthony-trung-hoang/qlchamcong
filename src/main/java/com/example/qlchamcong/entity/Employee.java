package com.example.qlchamcong.entity;

import com.example.qlchamcong.HRSystem.entity.Department;

import java.util.Date;

public class Employee {
    private int employeeId;
    private String name;
    private RoleEmployee role;
    private Department department;
    private Date dateStartWork;

    public Employee(int employeeId, String employeeCode, String name, RoleEmployee role, Department department, Date dateStartWork) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.department = department;
        this.dateStartWork = dateStartWork;
    }

    public Employee(int employeeId, RoleEmployee role) {
        this.employeeId = employeeId;
        this.role = role;
    }

    public Employee(int employeeId, String name, RoleEmployee role) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Employee(int employeeId, String name, RoleEmployee role, Department department, Date dateStartWork) {
        this.employeeId = employeeId;
        this.name = name;
        this.role = role;
        this.department = department;
        this.dateStartWork = dateStartWork;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleEmployee getRole() {
        return role;
    }

    public void setRole(RoleEmployee role) {
        this.role = role;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getDateStartWork() {
        return dateStartWork;
    }

    public void setDateStartWork(Date dateStartWork) {
        this.dateStartWork = dateStartWork;
    }

    public String getEmployeeToString() {
        return String.valueOf(employeeId);
    }
}
