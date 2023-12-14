package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.AttendanceRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLAttendanceRecordRepository implements IAttendanceRecordRepository{

    List<AttendanceRecord> attendanceRecordList = new ArrayList<>();

    @Override
    public List<AttendanceRecord> getAllAttendanceRecord() {
        for (int i = 1; i <= 10; i++) {
           AttendanceRecord sampleData = createSampleObject(i);
            attendanceRecordList.add(sampleData);
        }

        return this.attendanceRecordList;
    }

    //    mock data
    private AttendanceRecord createSampleObject(int i) {
        int employeeId = 1;
        String timeStamp = "Time" + i;
        int timeKeeperId = 12;
        Date date = new Date();

        return new AttendanceRecord(i, employeeId, timeStamp, timeKeeperId, date);
    }
}
