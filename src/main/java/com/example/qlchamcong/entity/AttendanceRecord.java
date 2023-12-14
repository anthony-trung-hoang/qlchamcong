package com.example.qlchamcong.entity;

import java.sql.Timestamp;

public class AttendanceRecord {
    private int id;

    private int employeeId;

    private String time;

    private int timeKeeperId;

    private Timestamp timestamp;

    private String type;

    public AttendanceRecord(int id, int employeeId, String time, int timeKeeperId, Timestamp timestamp, String type) {
        this.id = id;
        this.employeeId = employeeId;
        this.time = time;
        this.timeKeeperId = timeKeeperId;
        this.timestamp = timestamp;
        this.type = type;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTimeKeeperId() {
        return timeKeeperId;
    }

    public void setTimeKeeperId(int timeKeeperId) {
        this.timeKeeperId = timeKeeperId;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
