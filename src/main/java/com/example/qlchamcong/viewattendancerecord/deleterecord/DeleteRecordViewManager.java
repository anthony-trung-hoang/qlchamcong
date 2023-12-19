package com.example.qlchamcong.viewattendancerecord.deleterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteRecordViewManager implements Initializable {
    AttendanceRecord currentRecord;

    DeleteRecordController deleteRecordController;
    @FXML
    public Label timeStampLabel;
    @FXML
    public Label timeKeeperIdLabel;

    @FXML
    public void cancelButtonAction() {
    }

    @FXML
    public void confirmButtonAction() throws IOException {
        // user sure to delete that record
        deleteRecordController.deleteAndCloseModal(currentRecord);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        deleteRecordController = new DeleteRecordController(navUtil, argumentUtil);
        currentRecord = getInitialData();
        this.setInitialData(currentRecord);
    }

    public AttendanceRecord getInitialData() {
        return (AttendanceRecord) deleteRecordController.getInitialData();
    }

    private void setInitialData(AttendanceRecord attendanceRecord) {
        timeKeeperIdLabel.setText(attendanceRecord.getTimeKeeperId() +"");
        timeStampLabel.setText(attendanceRecord.getTimestamp().toString());
    }
}
