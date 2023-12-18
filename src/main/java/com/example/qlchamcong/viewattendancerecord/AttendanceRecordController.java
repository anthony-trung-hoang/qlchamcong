package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.IViewAttendanceRecordsService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AttendanceRecordController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;

    private final IViewAttendanceRecordsService viewAttendanceRecordsService;

    public AttendanceRecordController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        this.viewAttendanceRecordsService = ServiceInitializer.getViewAttendanceRecordsService();
    }

    public Object getInitialData() {
        return argumentUtil.getSharedData("fromHomeToAttendanceRecord");
    }

    public List<AttendanceRecord> fetchListOfRecords(int employeeId, Date date) {
        System.out.println("From controller: " + employeeId + ", " + date);
        return viewAttendanceRecordsService.getRecordsOfAnEmployeeInADay(employeeId, date);
    }

    public void showUpdateModal(AttendanceRecord attendanceRecord) throws IOException {
        argumentUtil.setSharedData("fromAttendanceRecordToUpdateRecord", attendanceRecord);
        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/add-record.fxml", "Update attendance record");
    }
}
