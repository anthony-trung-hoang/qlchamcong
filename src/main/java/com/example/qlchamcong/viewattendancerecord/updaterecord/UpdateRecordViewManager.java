package com.example.qlchamcong.viewattendancerecord.updaterecord;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
    private AttendanceRecord currentRecord;

    public void saveButtonAction() throws ParseException, IOException {
        AttendanceRecord newRecord = getNewRecordFromUI();
        System.out.println(newRecord);
        updateRecordController.saveNewRecord(newRecord);
        updateRecordController.closeModal();
    }

    private AttendanceRecord getNewRecordFromUI() throws ParseException {
        String newTimestamp = timeTextField.getText();
        int newTimekeeperId = Integer.parseInt(timeKeeperIdTextField.getText());
        Timestamp updatedTimestamp = getTimestamp(newTimestamp);

//        System.out.println("Chuỗi ban đầu: " + newTimestamp);
//        System.out.println("Timestamp sau cập nhật: " + updatedTimestamp);
//        System.out.println("New Timekeeper Id: " + newTimekeeperId);
        return new AttendanceRecord(currentRecord.getId(), currentRecord.getEmployeeId(), updatedTimestamp, newTimekeeperId, currentRecord.getType());

    }

    private Timestamp getTimestamp(String newTimestamp) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date parsedDate = dateFormat.parse(newTimestamp);

        Timestamp currentTs = currentRecord.getTimestamp();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTs.getTime());

        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTime(parsedDate);

        calendar.set(Calendar.HOUR_OF_DAY, newCalendar.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, newCalendar.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, newCalendar.get(Calendar.SECOND));

        return new Timestamp(calendar.getTimeInMillis());
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
        currentRecord = (AttendanceRecord) updateRecordController.getInitialData();
        return currentRecord;
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
