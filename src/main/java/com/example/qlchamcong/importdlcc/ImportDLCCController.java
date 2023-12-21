package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.exception.InvalidFileFormatException;
import com.example.qlchamcong.service.IImportDLCCService;

import java.io.File;
import java.util.List;

public class ImportDLCCController {

    private IImportDLCCService importDLCCService;

    public ImportDLCCController(IImportDLCCService importDLCCService) {
        this.importDLCCService = importDLCCService;
    }

    public List<AttendanceRecord> getAttendanceRecord(File attedanceRecordCheckInFile, File attedanceRecordCheckOutFile) throws InvalidFileFormatException {
        return importDLCCService.getAttendanceRecord(attedanceRecordCheckInFile, attedanceRecordCheckOutFile);
    }

    public Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) {
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
}
