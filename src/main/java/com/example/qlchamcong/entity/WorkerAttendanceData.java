package com.example.qlchamcong.entity;

import java.util.Date;

public class WorkerAttendanceData {
    private int id;
    private int employeeId;
    private Date date;
    private double hoursShift1;
    private double hoursShift2;
    private double hoursShift3;

    @Override
    public String toString() {
        return "WorkerAttendanceData{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", date=" + date +
                ", hoursShift1=" + hoursShift1 +
                ", hoursShift2=" + hoursShift2 +
                ", hoursShift3=" + hoursShift3 +
                '}';
    }

    public WorkerAttendanceData() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getHoursShift1() {
        return hoursShift1;
    }

    public void setHoursShift1(double hoursShift1) {
        this.hoursShift1 = hoursShift1;
    }

    public double getHoursShift2() {
        return hoursShift2;
    }

    public void setHoursShift2(double hoursShift2) {
        this.hoursShift2 = hoursShift2;
    }

    public double getHoursShift3() {
        return hoursShift3;
    }

    public void setHoursShift3(double hoursShift3) {
        this.hoursShift3 = hoursShift3;
    }
}
