package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.util.List;

public interface IHomeScreenService {
    List<WorkerAttendanceData> getWorkerAttendanceData();

    List<OfficerAttendanceData> getOfficerAttendanceData();
}
