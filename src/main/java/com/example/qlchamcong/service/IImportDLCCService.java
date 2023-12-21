package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.exception.ConflictSavedAttendanceRecord;
import com.example.qlchamcong.exception.InvalidFileFormatException;
import com.example.qlchamcong.exception.MoreThanTwoRoleInRecords;
import com.example.qlchamcong.exception.TransformException;

import java.io.File;
import java.util.List;

public interface IImportDLCCService {

    List<AttendanceRecord> getAttendanceRecord(File attedanceRecordFile, String timekeeperCheckInCodesValue, File attedanceRecordCheckOutFile, String timekeeperCheckOutCodesValue) throws InvalidFileFormatException, ConflictSavedAttendanceRecord;

    Tuple2<WorkerAttendanceData, OfficerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) throws TransformException;

    List<String> getAllTimekeeperCode();

    List<String> getAllTimekeeperCheckInCode();

    List<String> getAllTimekeeperCheckOutCode();

    void saveAttendanceData(Tuple2<WorkerAttendanceData, OfficerAttendanceData> transformedData, List<AttendanceRecord> attendanceRecordList);
}
