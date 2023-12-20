package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.Department;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;

import java.util.List;

public class ReportAttendanceService implements IReportAttendanceService {
    private IAttendanceRecordRepository attendanceRecordRepository;

    public ReportAttendanceService() {
//        this.attendanceRecordRepository = RepositoryInitializer.getAttendanceRecordRepository();
    }


    @Override
    public List<Department> getDepartmentList() {
        return null;
    }
}
