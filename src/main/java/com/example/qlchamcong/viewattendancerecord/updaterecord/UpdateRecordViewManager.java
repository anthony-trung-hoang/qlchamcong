package com.example.qlchamcong.viewattendancerecord.updaterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class UpdateRecordViewManager implements Initializable {

    UpdateRecordController updateRecordController;
    @FXML
    public Label employeeIdLabel;
    @FXML
    public Label attendanceDateLabel;

    @FXML
    public TextField timeTextField;

    @FXML
    public TextField timeKeeperIdTextField;

    public void saveButtonAction(ActionEvent event) {
    }

    public void cancelButtonAction(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        this.updateRecordController = new UpdateRecordController(navUtil, argumentUtil);
        this.setInitialData(getInitialData());
    }

    public AttendanceRecord getInitialData() {
        return (AttendanceRecord) updateRecordController.getInitialData();
    }

    private void setInitialData(AttendanceRecord attendanceRecord) {
        Timestamp timestamp = attendanceRecord.getTimestamp();
        String employeeId = attendanceRecord.getEmployeeId() + "";
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        String timeKeeperId = attendanceRecord.getTimeKeeperId() + "";

        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();

        employeeIdLabel.setText(employeeId);
        attendanceDateLabel.setText(timestamp.toString());
        timeTextField.setPromptText(hour + ":" + minute + ":" + second);
        timeKeeperIdTextField.setFocusTraversable(false);
        timeTextField.setFocusTraversable(false);
        timeKeeperIdTextField.setPromptText(timeKeeperId);
    }


}
