package com.example.qlchamcong.service;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.Employee;
import com.example.qlchamcong.entity.OfficerReportAttendanceRow;
import com.example.qlchamcong.entity.WorkerReportAttendanceRow;

import java.util.Date;
import java.util.List;

public interface IReportAttendanceService {
    List<Department> getDepartmentList();
    List<OfficerReportAttendanceRow> getOfficerReportAttendanceList(int id, Date date);
    List<WorkerReportAttendanceRow> getWorkerReportAttendanceList(int id, Date date);
}
