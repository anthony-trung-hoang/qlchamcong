package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.service.IHomeScreenService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class HRHomeController {

    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private final IHomeScreenService homeScreenService;

    public HRHomeController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
        this.homeScreenService = ServiceInitializer.getHomeScreenService();
    }

    public List<WorkerAttendanceData> fetchTableWorkerData(Date date) {
        return homeScreenService.getWorkerAttendanceData(date);
    }

    public void showDetails(WorkerAttendanceData data) throws IOException {
        argumentUtil.setSharedData("fromHomeToAttendanceRecord", data);
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public void showDetails(OfficerAttendanceData officerAttendanceData) throws IOException {
        argumentUtil.setSharedData("fromHomeToAttendanceRecord", officerAttendanceData);
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public List<OfficerAttendanceData> fetchTableOfficerData(Date date) {
        return homeScreenService.getOfficerAttendanceData(date);
    }
}
