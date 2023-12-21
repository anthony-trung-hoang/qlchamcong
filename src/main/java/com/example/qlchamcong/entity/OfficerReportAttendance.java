package com.example.qlchamcong.entity;

public class OfficerReportAttendance {
    private int employeeId;
    private  String employeeName;
    private  String departmentName;
    private String month ;
    private  int totalWorkSession;
    private  double totalHoursLate;
    private  double totalHoursLeavingEarly;


    public OfficerReportAttendance(int employeeId, String employeeName, String departmentName, String month, int totalWorkSession, double totalHoursLate, double totalHoursLeavingEarly) {
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

    public int getTotalWorkSession() {
        return totalWorkSession;
    }

    public double getTotalHoursLate() {
        return totalHoursLate;
    }

    public double getTotalHoursLeavingEarly() {
        return totalHoursLeavingEarly;
    }
}
