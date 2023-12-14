package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;

public class AttendanceRecordController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;

    public AttendanceRecordController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
    }

    public int getEmployeeId() {
        return (int) argumentUtil.getSharedData("fromHomeToAttendanceRecord");
    }
}
