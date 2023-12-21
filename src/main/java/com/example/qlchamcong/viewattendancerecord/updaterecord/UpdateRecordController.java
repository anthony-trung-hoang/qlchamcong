package com.example.qlchamcong.viewattendancerecord.updaterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.service.viewattendancerecord.IViewAttendanceRecordsService;
import com.example.qlchamcong.service.ServiceInitializer;

import java.io.IOException;

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

    public void closeModal() throws IOException {
        navUtil.closeModal();
        navUtil.changeGUI("/com/example/qlchamcong/viewattendancerecord/view-attendance-record.fxml");
    }

    public void saveNewRecord(AttendanceRecord newRecord) throws IOException {
        viewAttendanceRecordsService.updateRecordAndUpdateAttendanceDataAccordingly(newRecord);
        closeModal();
    }
}
