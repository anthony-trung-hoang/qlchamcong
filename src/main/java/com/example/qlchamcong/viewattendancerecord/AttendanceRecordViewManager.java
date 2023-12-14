package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceRecordViewManager implements Initializable {

    private AttendanceRecordController attendanceRecordController;
    @FXML
    public Label pageTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        attendanceRecordController = new AttendanceRecordController(navUtil, argumentUtil);

        int shareData = attendanceRecordController.getEmployeeId();
        this.pageTitle.setText(shareData +"");

        List<AttendanceRecord> fetchedData = attendanceRecordController.fetchListOfRecords(1, new Date());
        System.out.println(fetchedData);
    }
}
