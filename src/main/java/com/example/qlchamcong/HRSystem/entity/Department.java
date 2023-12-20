package com.example.qlchamcong.HRSystem.entity;

import java.util.Date;

public class Department {
    private int id;
    private String name;
    private int totalEmployee;
    private Date founding;
    private RoleDepartment type;

    public Department(int id, String name, int totalEmployee, Date founding, RoleDepartment type) {
        this.id = id;
        this.name = name;
        this.totalEmployee = totalEmployee;
        this.founding = founding;
        this.type = type;
    }

    // Thêm các phương thức getter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalEmployee() {
        return totalEmployee;
    }

    public Date getFounding() {
        return founding;
    }

    public RoleDepartment getType() {
        return type;
    }
}
