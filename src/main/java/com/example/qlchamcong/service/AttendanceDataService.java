package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.IWorkerAttendanceDataRepository;
import com.example.qlchamcong.repository.RepositoryInitializer;

import java.util.List;

public class AttendanceDataService implements  IAttendanceDataService{
    private IWorkerAttendanceDataRepository workerAttendanceDataRepository;

    public AttendanceDataService() {
        this.workerAttendanceDataRepository = RepositoryInitializer.getWorkerAttendanceDataRepository();
    }
    @Override
    public List<WorkerAttendanceData> getWorkerAttendanceData() {
        return workerAttendanceDataRepository.getAllAttendanceData();
    }
}
