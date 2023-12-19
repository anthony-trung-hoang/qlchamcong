package com.example.qlchamcong.viewattendancerecord.updaterecord;

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

    public void saveButtonAction() {
    }

    public void cancelButtonAction() throws IOException {
        System.out.println("Cancel button clicked");
        updateRecordController.closeModal();
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
        String employeeId = attendanceRecord.getEmployeeId() + "";
        String timeKeeperId = attendanceRecord.getTimeKeeperId() + "";

        employeeIdLabel.setText(employeeId);
        attendanceDateLabel.setText(attendanceRecord.getFormattedDate());
        timeTextField.setPromptText(attendanceRecord.getFormattedTime());
        timeKeeperIdTextField.setFocusTraversable(false);
        timeTextField.setFocusTraversable(false);
        timeKeeperIdTextField.setPromptText(timeKeeperId);
    }


}
