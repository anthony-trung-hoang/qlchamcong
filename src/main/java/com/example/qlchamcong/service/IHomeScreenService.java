package com.example.qlchamcong.service;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.sql.Date;
import java.util.List;

public interface IHomeScreenService {
    List<WorkerAttendanceData> getWorkerAttendanceData(Date date);

    List<OfficerAttendanceData> getOfficerAttendanceData(Date date);
}
