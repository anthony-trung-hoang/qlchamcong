package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLWorkerAttendanceDataRepository implements IWorkerAttendanceDataRepository {


    List<WorkerAttendanceData> workerAttendanceDataList = new ArrayList<>();

    @Override
    public List<WorkerAttendanceData> getAllAttendanceData() {
        workerAttendanceDataList.add(createSampleObject(1L, 101L, new Date(), 8.0, 7.5, 6.5));
        workerAttendanceDataList.add(createSampleObject(2L, 102L, new Date(), 7.0, 7.5, 8.0));
        workerAttendanceDataList.add(createSampleObject(3L, 103L, new Date(), 6.0, 6.5, 7.5));
        workerAttendanceDataList.add(createSampleObject(4L, 104L, new Date(), 8.5, 8.0, 7.0));
        workerAttendanceDataList.add(createSampleObject(5L, 105L, new Date(), 7.5, 7.0, 6.0));

        return this.workerAttendanceDataList;
    }

    //    mock data
    private WorkerAttendanceData createSampleObject(Long id, Long staffId, Date workDate,
                                                    double hoursForShift1, double hoursForShift2, double hoursForShift3) {
        return new WorkerAttendanceData(id, staffId, workDate, hoursForShift1, hoursForShift2, hoursForShift3);
    }
}
