package com.example.qlchamcong.viewattendancerecord.addrecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRecordViewManager implements Initializable {
    AddRecordController updateRecordController;
    @FXML
    public Label employeeIdLabel;
    @FXML
    public Label attendanceDateLabel;

    @FXML
    public TextField timeTextField;

    @FXML
    public TextField timeKeeperIdTextField;

    public void saveButtonAction() {

    }

    public void cancelButtonAction() throws IOException {
        System.out.println("Button clicked");
        updateRecordController.returnToAttendanceRecordListView();
    }

//    public AttendanceRecord getNewRecordFromUI() {
//
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        this.updateRecordController = new AddRecordController(navUtil, argumentUtil);
        this.setInitialData(getInitialData());
    }

    public AttendanceRecord getInitialData() {
        return (AttendanceRecord) updateRecordController.getInitialData();
    }

    private void setInitialData(AttendanceRecord attendanceRecord) {
        String employeeId = attendanceRecord.getEmployeeId() + "";
        String timeKeeperId = attendanceRecord.getTimeKeeperId() + "";

        employeeIdLabel.setText(employeeId);
        attendanceDateLabel.setText(attendanceRecord.getFormattedDate());
    }

}
