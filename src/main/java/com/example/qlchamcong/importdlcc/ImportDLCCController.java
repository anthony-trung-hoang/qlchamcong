package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.exception.ConflictSavedAttendanceRecord;
import com.example.qlchamcong.exception.InvalidFileFormatException;
import com.example.qlchamcong.exception.MoreThanTwoRoleInRecords;
import com.example.qlchamcong.exception.TransformException;
import com.example.qlchamcong.service.IImportDLCCService;

import java.io.File;
import java.util.List;

public class ImportDLCCController {

    private IImportDLCCService importDLCCService;

    public ImportDLCCController(IImportDLCCService importDLCCService) {
        this.importDLCCService = importDLCCService;
    }

    public List<AttendanceRecord> getAttendanceRecord(File attedanceRecordCheckInFile, String timekeeperCheckInCodesValue, File attedanceRecordCheckOutFile, String timekeeperCheckOutCodesValue) throws InvalidFileFormatException, ConflictSavedAttendanceRecord {
        return importDLCCService.getAttendanceRecord(attedanceRecordCheckInFile, timekeeperCheckInCodesValue, attedanceRecordCheckOutFile, timekeeperCheckOutCodesValue);
    }

    public Tuple2<WorkerAttendanceData, OfficerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) throws TransformException {
        return importDLCCService.getTransformedData(attendanceRecordList);
    }

    public List<String> getAllTimekeeperCode() {
        return importDLCCService.getAllTimekeeperCode();
    }

    public List<String> getAllTimekeeperCheckInCode() {
        return importDLCCService.getAllTimekeeperCheckInCode();
    }

    public List<String> getAllTimekeeperCheckOutCode() {
        return importDLCCService.getAllTimekeeperCheckOutCode();
    }

    public void saveAttendanceData(Tuple2<WorkerAttendanceData, OfficerAttendanceData> transformedData, List<AttendanceRecord> attendanceRecordList) {
        importDLCCService.saveAttendanceData(transformedData, attendanceRecordList);
    }
}
