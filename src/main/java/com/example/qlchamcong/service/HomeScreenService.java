package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.util.List;

public class HomeScreenService implements IHomeScreenService {
    private final IWorkerAttendanceDataRepository workerAttendanceDataRepository;

    public HomeScreenService() {
        this.workerAttendanceDataRepository = RepositoryInitializer.getWorkerAttendanceDataRepository();
    }
    @Override
    public List<WorkerAttendanceData> getWorkerAttendanceData() {
        return workerAttendanceDataRepository.getAllAttendanceData();
    }
}
