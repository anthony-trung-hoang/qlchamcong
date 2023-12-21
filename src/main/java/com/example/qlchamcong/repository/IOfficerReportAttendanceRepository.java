package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.Date;
import java.util.List;

public interface IOfficerReportAttendanceRepository {
    List<OfficerAttendanceData> getOfficerReportAttendance(Date startDate, Date endDate, List<Integer> employeeIdList);
}
