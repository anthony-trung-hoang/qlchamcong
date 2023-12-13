package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.service.*;

import java.util.List;

public class QLNSHomeController {

    private IAttendanceDataService attendanceDataService;

    public QLNSHomeController() {
        this.attendanceDataService = ServiceInitializer.getAttendanceDataService();
    }

    public List<WorkerAttendanceData> fetchTableData() {
        List<WorkerAttendanceData> workerAttendanceData = attendanceDataService.getWorkerAttendanceData();
        return workerAttendanceData;
    }

}
