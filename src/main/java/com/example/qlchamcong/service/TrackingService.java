package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.IEmployeeRepository;

import java.sql.Date;
import java.util.List;

public class TrackingService implements ITrackingService {
    private final IEmployeeRepository employeeRepository;
    private final IAttendanceRecordRepository attendanceRecordRepository;

    public TrackingService(IEmployeeRepository employeeRepository, IAttendanceRecordRepository attendanceRecordRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.layTatCaEmployee();
    }

    @Override
    public List<AttendanceRecord> getEmployeeAttendanceRecord(Employee employee, Date dateSearch) {
        return attendanceRecordRepository.getAttendanceRecordsByEmployeeAndDate(employee.getId(), dateSearch);
    }
}
