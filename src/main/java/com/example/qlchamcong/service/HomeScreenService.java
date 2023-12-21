package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.IOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.sql.Date;
import java.util.List;

public class HomeScreenService implements IHomeScreenService {
    private final IWorkerAttendanceDataRepository workerAttendanceDataRepository;

    private final IOfficerAttendanceDataRepository officerAttendanceDataRepository;

    public HomeScreenService() {
        this.officerAttendanceDataRepository = RepositoryInitializer.getOfficerAttendanceDataRepository();
        this.workerAttendanceDataRepository = RepositoryInitializer.getWorkerAttendanceDataRepository();
    }
    @Override
    public List<WorkerAttendanceData> getWorkerAttendanceData() {
        Date date = Date.valueOf("2023-01-01");
        return workerAttendanceDataRepository.getAllWorkerAttendanceDataByDate(date);
    }

    @Override
    public List<OfficerAttendanceData> getOfficerAttendanceData() {
        Date date = Date.valueOf("2023-01-01");
        return officerAttendanceDataRepository.getAllOfficerAttendanceDataByDate(date);
    }
}
