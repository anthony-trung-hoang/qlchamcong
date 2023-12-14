package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.service.*;

import java.io.IOException;
import java.util.List;

public class QLNSHomeController {

    private final IActionChangeGUI navUtil;
    private IAttendanceDataService attendanceDataService;

    public QLNSHomeController(IActionChangeGUI navUtil) {
        this.navUtil = navUtil;
        this.attendanceDataService = ServiceInitializer.getAttendanceDataService();
    }

    public List<WorkerAttendanceData> fetchTableData() {
        return attendanceDataService.getWorkerAttendanceData();
    }

    public void showDetails() throws IOException {
//        navUtil.changeGUI("/com/example/qlchamcong/qlnshome/qlns-home.fxml");

        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

}
