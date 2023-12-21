package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.Employee;

import java.sql.Date;
import java.util.List;

public interface ITrackingService {
    List<Employee> getAllEmployee();

    List<AttendanceRecord> getEmployeeAttendanceRecord(Employee employee, Date dateSearch);
}
