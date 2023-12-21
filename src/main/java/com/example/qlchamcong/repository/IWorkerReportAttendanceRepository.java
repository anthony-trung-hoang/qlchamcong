package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.List;

public interface IWorkerReportAttendanceRepository {
    List<WorkerAttendanceData> getWorkerReportAttendance(String startDate, String endDate, List<Integer> employeeIdList);
}
