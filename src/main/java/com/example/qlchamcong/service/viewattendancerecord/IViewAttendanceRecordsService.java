package com.example.qlchamcong.service.viewattendancerecord;

import com.example.qlchamcong.entity.AttendanceRecord;

import java.util.Date;
import java.util.List;

public interface IViewAttendanceRecordsService {
    List<AttendanceRecord> getRecordsOfAnEmployeeInADay(int employeeId, Date date);

    void deleteRecord(AttendanceRecord currentRecord);

    void createANewRecord(AttendanceRecord newRecord);

    void updateRecord(AttendanceRecord newRecord);

    void deleteRecordAndUpdateAttendanceDataAccordingly(AttendanceRecord record);

    void createRecordAndUpdateAttendanceDataAccordingly(AttendanceRecord record);

    void updateRecordAndUpdateAttendanceDataAccordingly(AttendanceRecord newRecord);

    void updateOfficerAttendanceData(AttendanceRecord newRecord);

    void updateWorkerAttendanceData(AttendanceRecord newRecord);

    void updateAttendanceData(AttendanceRecord record);
}
