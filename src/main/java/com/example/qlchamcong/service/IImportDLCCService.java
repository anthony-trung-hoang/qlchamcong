package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.io.File;
import java.util.List;

public interface IImportDLCCService {

    List<AttendanceRecord> getAttendanceRecord(File attedanceRecordFile);
    Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList);
}
