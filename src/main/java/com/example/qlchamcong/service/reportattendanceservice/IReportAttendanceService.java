package com.example.qlchamcong.service.reportattendanceservice;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.entity.WorkerReportAttendance;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IReportAttendanceService {
    List<Department> getDepartmentList();
    List<OfficerReportAttendance> getOfficerReportAttendanceList(int id, String month,String year);
    List<WorkerReportAttendance> getWorkerReportAttendanceList(int id, String month,String year);

    List<WorkerReportAttendance> getWorkerReportAttendance(List<Employee> employees, List<WorkerAttendanceData> attendances, String number, String number1);
}
