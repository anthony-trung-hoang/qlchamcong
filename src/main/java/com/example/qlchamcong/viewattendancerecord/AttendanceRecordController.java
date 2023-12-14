package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.IAttendanceRecordService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AttendanceRecordController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;

    private IAttendanceRecordService attendanceRecordService;

    public AttendanceRecordController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.attendanceRecordService = ServiceInitializer.getAttendanceRecordService();
    }

    public int getEmployeeId() {
        return (int) argumentUtil.getSharedData("fromHomeToAttendanceRecord");
    }

    public List<AttendanceRecord> fetchListOfRecords(int employeeId, Date date) {
        System.out.println("From controller: " + employeeId + ", " + date);
        return attendanceRecordService.getRecordsOfAnEmployeeInADay(employeeId, date);
    }

    public void showUpdateModal() throws IOException {
        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/add-record.fxml", "Update attendance record");
    }
}
