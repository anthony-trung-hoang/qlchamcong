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
        return viewAttendanceRecordsService.getRecordsOfAnEmployeeInADay(employeeId, date);
    }

    public void showUpdateModal(AttendanceRecord attendanceRecord) throws IOException {
        argumentUtil.setSharedData("fromAttendanceRecordToUpdateRecord", attendanceRecord);
        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/update-record.fxml", "Update attendance record");
    }

    public void showAddNewRecordModal(AttendanceRecord attendanceRecord) throws IOException {
        argumentUtil.setSharedData("fromAttendanceRecordToNewRecord", attendanceRecord);
        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/add-record.fxml", "Add a new record");
    }

    public void showDeleteModal(AttendanceRecord attendanceRecord) throws IOException {
        argumentUtil.setSharedData("fromAttendanceRecordToDeleteRecord", attendanceRecord);
        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/confirm-delete.fxml", "Confirm deletion");
    }
}
