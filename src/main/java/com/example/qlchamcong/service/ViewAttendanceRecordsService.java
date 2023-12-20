package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.util.Date;
import java.util.List;

public class ViewAttendanceRecordsService implements IViewAttendanceRecordsService {
    private final IAttendanceRecordRepository attendanceRecordRepository;

    public ViewAttendanceRecordsService() {
        this.attendanceRecordRepository = RepositoryInitializer.getAttendanceRecordRepository();
    }

    @Override
    public List<AttendanceRecord> getRecordsOfAnEmployeeInADay(int employeeId, Date date) {
        return attendanceRecordRepository.getAttendanceRecordsByEmployeeAndDate(employeeId, (java.sql.Date) date);
    }

    @Override
    public void deleteRecord(AttendanceRecord currentRecord) {
        attendanceRecordRepository.deleteRecordById(currentRecord.getId());
    }

    @Override
    public void createANewRecord(AttendanceRecord newRecord) {
        attendanceRecordRepository.createANewRecord(newRecord);
    }

    @Override
    public void updateRecord(AttendanceRecord newRecord) {
        attendanceRecordRepository.updateRecordById(newRecord);
    }
}
