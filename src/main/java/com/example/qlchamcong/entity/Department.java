package com.example.qlchamcong.entity;

import java.sql.Timestamp;

public class Department {
    private int id;

    private String name;

    private int totalEmployee;


    private Timestamp founding;

    private String type;

    public Department(int id, String name, int totalEmployee,  Timestamp founding, String type) {
        this.id = id;
        this.name = name;
        this.totalEmployee = totalEmployee;
        this.founding = founding;
        this.type = type;
    }
}
