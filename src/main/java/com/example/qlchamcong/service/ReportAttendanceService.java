package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.HRSystemAPIService;
import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.Employee;

import java.util.List;

public class ReportAttendanceService implements IReportAttendanceService {
//    private IReportAttendanceService reportAttendanceService;
    private IHRSystemAPIService hRSystemAPIService;
    public ReportAttendanceService() {
        hRSystemAPIService= new HRSystemAPIService();
    }


    @Override
    public List<Department> getDepartmentList() {
        return hRSystemAPIService.getDeparmentList();
    }

    @Override
    public List<Employee> getEmployeeList() {
        return hRSystemAPIService.getEmployeeList();
    }
}
