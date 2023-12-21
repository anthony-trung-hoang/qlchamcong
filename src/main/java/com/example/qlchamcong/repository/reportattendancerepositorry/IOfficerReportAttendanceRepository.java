package com.example.qlchamcong.repository.reportattendancerepositorry;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.Date;
import java.util.List;

public interface IOfficerReportAttendanceRepository {
    List<OfficerAttendanceData> getOfficerReportAttendance(String startDate, String endDate, List<Integer> employeeIdList);
}
