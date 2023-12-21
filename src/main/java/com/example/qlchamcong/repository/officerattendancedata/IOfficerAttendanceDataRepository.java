package com.example.qlchamcong.repository.officerattendancedata;

import com.example.qlchamcong.entity.OfficerAttendanceData;

import java.util.Date;
import java.util.List;

public interface IOfficerAttendanceDataRepository {
    OfficerAttendanceData getOfficerAttendanceDataByEmployeeAndDate(int employeeId, Date date);

    void updateOfficerAttendanceData(int id, boolean morningSession, boolean afternoonSession, double lateHours, double earlyLeaveHours);

    List<OfficerAttendanceData> getAllOfficerAttendanceDataByDate(java.sql.Date date);
}
