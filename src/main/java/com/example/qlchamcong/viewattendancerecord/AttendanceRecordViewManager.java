package com.example.qlchamcong.viewattendancerecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AttendanceRecordViewManager implements Initializable {

    private AttendanceRecordController attendanceRecordController;
    @FXML
    public Label pageTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        attendanceRecordController = new AttendanceRecordController(navUtil);
    }
}
