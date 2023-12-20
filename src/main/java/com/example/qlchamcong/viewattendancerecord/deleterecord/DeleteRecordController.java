package com.example.qlchamcong.viewattendancerecord.deleterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.IViewAttendanceRecordsService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;

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

    public void deleteAndCloseModal(AttendanceRecord currentRecord) throws IOException {
        viewAttendanceRecordsService.deleteRecord(currentRecord);
        navUtil.closeModal();
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public void closeModal() throws IOException {
        navUtil.closeModal();
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }
}
