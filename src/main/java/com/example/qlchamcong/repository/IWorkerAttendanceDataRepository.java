package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.Date;
import java.util.List;

public interface IWorkerAttendanceDataRepository {
    List<WorkerAttendanceData> getAllWorkerAttendanceDataByDate(Date date);

    WorkerAttendanceData getWorkerAttendanceDataByEmployeeAndDate(int employeeId, Date date);

    void updateWorkerAttendanceData(int id, double hoursShift1, double hoursShift2, double hoursShift3);

    void saveWorkerAttendanceDataList(List<WorkerAttendanceData> workerAttendanceDataList);
}
