package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.util.Date;
import java.util.List;

public class AttendanceRecordService implements  IAttendanceRecordService {
    private IAttendanceRecordRepository attendanceRecordRepository;

    public AttendanceRecordService() {
        this.attendanceRecordRepository = RepositoryInitializer.getAttendanceRecordRepository();
    }

    @Override
    public List<AttendanceRecord> getRecordsOfAnEmployeeInADay(int employeeId, Date date) {
        System.out.println(employeeId + " and " + date);
        return attendanceRecordRepository.getAllAttendanceRecord();
    }
}
