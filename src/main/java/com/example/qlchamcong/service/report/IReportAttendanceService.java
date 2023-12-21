package com.example.qlchamcong.service.report;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.Employee;

import java.util.List;

public interface IReportAttendanceService {
    List<Department> getDepartmentList();
    List<Employee> getEmployeeList();
}
