package com.example.qlchamcong.entity;

public class Timekeeper {
    private int id;

    private String timekeeperCode;

    private String type;

    public Timekeeper(int id, String timekeeperCode, String type) {
        this.id = id;
        this.timekeeperCode = timekeeperCode;
        this.type = type;
    }

    public Timekeeper(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimekeeperCode() {
        return timekeeperCode;
    }

    public void setTimekeeperCode(String timekeeperCode) {
        this.timekeeperCode = timekeeperCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
