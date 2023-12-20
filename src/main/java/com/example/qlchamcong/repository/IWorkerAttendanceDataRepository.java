package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.Date;
import java.util.List;

public interface IWorkerAttendanceDataRepository {
    List<WorkerAttendanceData> getAllWorkerAttendanceDataByDate(Date date);
}
