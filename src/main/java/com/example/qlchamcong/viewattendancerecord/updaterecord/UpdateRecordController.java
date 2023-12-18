package com.example.qlchamcong.viewattendancerecord.updaterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.service.IViewAttendanceRecordsService;
import com.example.qlchamcong.service.ServiceInitializer;

public class UpdateRecordController {

    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;

    private final IViewAttendanceRecordsService viewAttendanceRecordsService;


    public UpdateRecordController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
        this.viewAttendanceRecordsService = ServiceInitializer.getViewAttendanceRecordsService();
    }

    public Object getInitialData() {
        return argumentUtil.getSharedData("fromAttendanceRecordToUpdateRecord");
    }
}
