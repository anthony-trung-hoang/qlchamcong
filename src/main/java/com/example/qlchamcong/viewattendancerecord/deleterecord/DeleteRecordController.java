package com.example.qlchamcong.viewattendancerecord.deleterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.service.IViewAttendanceRecordsService;
import com.example.qlchamcong.service.ServiceInitializer;

public class DeleteRecordController {
    private final IViewAttendanceRecordsService viewAttendanceRecordsService;
    IActionChangeGUI navUtil;
    IPassArgument argumentUtil;
    public DeleteRecordController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.navUtil = navUtil;
        this.argumentUtil = argumentUtil;
        this.viewAttendanceRecordsService = ServiceInitializer.getViewAttendanceRecordsService();
    }

    public Object getInitialData() {
        return argumentUtil.getSharedData("fromAttendanceRecordToDeleteRecord");
    }
}
