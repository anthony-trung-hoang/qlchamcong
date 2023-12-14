package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.service.*;

import java.io.IOException;
import java.util.List;

public class QLNSHomeController {

    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;
    private IAttendanceDataService attendanceDataService;

    public QLNSHomeController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
        this.attendanceDataService = ServiceInitializer.getAttendanceDataService();
    }

    public List<WorkerAttendanceData> fetchTableData() {
        return attendanceDataService.getWorkerAttendanceData();
    }

    public void showDetails(int id) throws IOException {
//        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");
        argumentUtil.setSharedData("fromHomeToAttendanceRecord", id);
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

}
