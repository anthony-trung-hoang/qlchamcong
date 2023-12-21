package com.example.qlchamcong.entity;

import java.util.Date;

public class OfficerReportAttendanceRow {
    private int employeeId;
    private  String employeeName;
    private  String departmentName;
    private String month ;
    private  double totalWorkSession;
    private  double totalHoursLate;
    private  double totalHoursLeavingEarly;


    public OfficerReportAttendanceRow(int employeeId, String employeeName, String departmentName, String month, double totalWorkSession, double totalHoursLate, double totalHoursLeavingEarly) {
    this.employeeId=employeeId;
    this.employeeName=employeeName;
    this.departmentName=departmentName;
    this.month = month;
    this.totalHoursLeavingEarly=totalHoursLeavingEarly;
    this.totalHoursLate =totalHoursLate;
    this.totalWorkSession=totalWorkSession;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getMonth() {
        return month;
    }

    public double getTotalWorkSession() {
        return totalWorkSession;
    }

    public double getTotalHoursLate() {
        return totalHoursLate;
    }

    public double getTotalHoursLeavingEarly() {
        return totalHoursLeavingEarly;
    }
}
