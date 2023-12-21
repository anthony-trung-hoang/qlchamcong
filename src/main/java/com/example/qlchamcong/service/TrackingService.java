package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.repository.IEmployeeRepository;

import java.util.List;

public class TrackingService implements ITrackingService {
    private final IEmployeeRepository employeeRepository;

    public TrackingService(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.layTatCaEmployee();
    }
}
