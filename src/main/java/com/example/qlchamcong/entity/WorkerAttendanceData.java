package com.example.qlchamcong.entity;

import java.util.Date;

public class WorkerAttendanceData {
    private Long id;
    private Long staffId;
    private Date workDate;
    private double hoursForShift1;
    private double hoursForShift2;
    private double hoursForShift3;

    // Constructors, getters, and setters

    public WorkerAttendanceData(Long id, Long staffId, Date workDate, double hoursForShift1, double hoursForShift2, double hoursForShift3) {
        this.id = id;
        this.staffId = staffId;
        this.workDate = workDate;
        this.hoursForShift1 = hoursForShift1;
        this.hoursForShift2 = hoursForShift2;
        this.hoursForShift3 = hoursForShift3;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public double getHoursForShift1() {
        return hoursForShift1;
    }

    public void setHoursForShift1(double hoursForShift1) {
        this.hoursForShift1 = hoursForShift1;
    }

    public double getHoursForShift2() {
        return hoursForShift2;
    }

    public void setHoursForShift2(double hoursForShift2) {
        this.hoursForShift2 = hoursForShift2;
    }

    public double getHoursForShift3() {
        return hoursForShift3;
    }

    public void setHoursForShift3(double hoursForShift3) {
        this.hoursForShift3 = hoursForShift3;
    }
}
