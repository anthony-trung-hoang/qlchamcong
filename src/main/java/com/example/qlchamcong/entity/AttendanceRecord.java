package com.example.qlchamcong.entity;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceRecord {
    private int id;

    private int employeeId;

    private int timeKeeperId;

    private Timestamp timestamp;

    private String type; // checkin or checkout

    public AttendanceRecord(int id, int employeeId, int timeKeeperId, Timestamp timestamp, String type) {
        this.id = id;
        this.employeeId = employeeId;
        this.timeKeeperId = timeKeeperId;
        this.timestamp = timestamp;
        this.type = type;
    }

    public AttendanceRecord() {

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

    public String getFormattedDate() {
        LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        return day + "/" + month + "/" + year;
    }

    public String getFormattedTime() {
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();

        return hour + ":" + minute + ":" + second;
    }

}
