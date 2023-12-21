package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.service.*;

import java.io.IOException;
import java.util.List;

public class HRHomeController {

    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private IHomeScreenService homeScreenService;

    public HRHomeController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
        this.homeScreenService = ServiceInitializer.getHomeScreenService();
    }

    public List<WorkerAttendanceData> fetchTableWorkerData() {
        return homeScreenService.getWorkerAttendanceData();
    }

    public void showDetails(WorkerAttendanceData data) throws IOException {
        argumentUtil.setSharedData("fromHomeToAttendanceRecord", data);
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public void showDetails(OfficerAttendanceData officerAttendanceData) throws IOException {
        argumentUtil.setSharedData("fromHomeToAttendanceRecord", officerAttendanceData);
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public List<OfficerAttendanceData> fetchTableOfficerData() {
        return homeScreenService.getOfficerAttendanceData();
    }
}
