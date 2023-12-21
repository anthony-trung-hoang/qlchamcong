package com.example.qlchamcong.service.importdata;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.exception.InvalidFileFormatException;

import java.io.File;
import java.util.List;

public interface IImportDLCCService {

    List<AttendanceRecord> getAttendanceRecord(File attedanceRecordFile, File attedanceRecordCheckOutFile) throws InvalidFileFormatException;

    Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList);

    List<String> getAllTimekeeperCode();

    List<String> getAllTimekeeperCheckInCode();

    List<String> getAllTimekeeperCheckOutCode();
}
