package com.example.qlchamcong.entity;

public class WorkerReportAttendanceRow {
    private int employeeId;
    private  String employeeName;
    private  String departmentName;
    private String month ;
    private  double totalWork;
    private  double totalOvertime;


    public WorkerReportAttendanceRow(int employeeId, String employeeName, String departmentName, String month, double totalWork, double totalOvertime) {
        this.employeeId=employeeId;
        this.employeeName=employeeName;
        this.departmentName=departmentName;
        this.month = month;
        this.totalWork =totalWork;
        this.totalOvertime=totalOvertime;
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

    public double getTotalWork() {
        return totalWork;
    }

    public double getTotalOvertime() {
        return totalOvertime;
    }


}
