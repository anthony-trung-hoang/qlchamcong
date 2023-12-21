package com.example.qlchamcong.entity;

import java.util.Date;

public class OfficerAttendanceData {
    private int id;
    private int employeeId;
    private Date date;
    private boolean morningSession;
    private boolean afternoonSession;
    private double hoursLate;
    private double hoursEarlyLeave;

    public OfficerAttendanceData(int id, int employeeId, Date date, boolean morningSession, boolean afternoonSession, double hoursLate, double hoursEarlyLeave) {
        this.id = id;
        this.employeeId = employeeId;
        this.date = date;
        this.morningSession = morningSession;
        this.afternoonSession = afternoonSession;
        this.hoursLate = hoursLate;
        this.hoursEarlyLeave = hoursEarlyLeave;
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

    public boolean isMorningSession() {
        return morningSession;
    }

    public void setMorningSession(boolean morningSession) {
        this.morningSession = morningSession;
    }

    public boolean isAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(boolean afternoonSession) {
        this.afternoonSession = afternoonSession;
    }

    public double getHoursLate() {
        return hoursLate;
    }

    public void setHoursLate(double hoursLate) {
        this.hoursLate = hoursLate;
    }

    public double getHoursEarlyLeave() {
        return hoursEarlyLeave;
    }

    public void setHoursEarlyLeave(double hoursEarlyLeave) {
        this.hoursEarlyLeave = hoursEarlyLeave;
    }
}
