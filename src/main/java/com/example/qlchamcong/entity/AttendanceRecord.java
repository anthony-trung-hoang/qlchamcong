package com.example.qlchamcong.entity;

import java.util.Date;

public class AttendanceRecord {
    private int id;

    private int employeeId;

    private String timeStamp;

    private int timeKeeperId;

    private Date date;

    public AttendanceRecord(int id, int employeeId, String timeStamp, int timeKeeperId, Date date) {
        this.id = id;
        this.employeeId = employeeId;
        this.timeStamp = timeStamp;
        this.timeKeeperId = timeKeeperId;
        this.date = date;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getTimeKeeperId() {
        return timeKeeperId;
    }

    public void setTimeKeeperId(int timeKeeperId) {
        this.timeKeeperId = timeKeeperId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
