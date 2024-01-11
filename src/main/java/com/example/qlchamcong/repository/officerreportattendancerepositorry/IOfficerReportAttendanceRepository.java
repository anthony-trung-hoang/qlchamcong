package com.example.qlchamcong.repository.officerreportattendancerepositorry;

import com.example.qlchamcong.entity.OfficerAttendanceData;

import java.util.List;

public interface IOfficerReportAttendanceRepository {
    List<OfficerAttendanceData> getOfficerReportAttendance(String startDate, String endDate, List<Integer> employeeIdList);
}
