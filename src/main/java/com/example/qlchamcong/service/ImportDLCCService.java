package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.IOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.ITimekeeperRepository;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;

import java.io.File;
import java.util.List;

public class ImportDLCCService implements IImportDLCCService {

    private final ITimekeeperRepository timekeeperRepository;
    private final IWorkerAttendanceDataRepository workerAttendanceRepository;
    private final IOfficerAttendanceDataRepository officerAttendanceDataRepository;

    public ImportDLCCService(ITimekeeperRepository timekeeperRepository, IWorkerAttendanceDataRepository workerAttendanceRepository, IOfficerAttendanceDataRepository officerAttendanceDataRepository) {
        this.timekeeperRepository = timekeeperRepository;
        this.workerAttendanceRepository = workerAttendanceRepository;
        this.officerAttendanceDataRepository = officerAttendanceDataRepository;
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecord(File attedanceRecordFile) {
        return null;
    }

    @Override
    public Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTransformedData(List<AttendanceRecord> attendanceRecordList) {
        return null;
    }
}
