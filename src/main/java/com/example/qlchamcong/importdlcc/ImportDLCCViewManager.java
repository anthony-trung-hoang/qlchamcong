package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.changeGUIUtility.PassArgumentUtil;
import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ImportDLCCViewManager implements Initializable {

    @FXML
    private TextField filePath;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private ComboBox<String> attendingMachineIds;
    @FXML
    private Label resultTitle;
    @FXML
    private VBox tableSpace;
    @FXML
    private Button saveAttendanceDataBtn;
    @FXML
    private Button transformRecordBtn;

    private ImportDLCCController importDLCCController;
    private File attedanceRecordFile;
    private TableView tableRecord = new TableView();
    private TableView tableWorker = new TableView();
    private TableView tableOfficer = new TableView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        importDLCCController = new ImportDLCCController(navUtil, argumentUtil);

        filePath.setDisable(true);
        attendingMachineIds.getItems().addAll(getAttendingMachineIDs());
        resultTitle.setVisible(false);
        saveAttendanceDataBtn.setVisible(false);
        transformRecordBtn.setVisible(false);
    }

    private List<String> getAttendingMachineIDs() {
        return List.of("AM01", "AM02", "AM03");
    }

    @FXML
    protected void handleChooseFileBtnAction() {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) chooseFileBtn.getScene().getWindow();
        try {
            attedanceRecordFile = fileChooser.showOpenDialog(stage);
        } catch (NullPointerException ex) {
            return;
        }
        if (attedanceRecordFile != null) {
            System.out.println(attedanceRecordFile);
        }
        filePath.setText(attedanceRecordFile.getPath());

        if (attendingMachineIds.getValue() != null) {
            System.out.println("can handle retrieve");
            handleRetrieve();
        }
    }

    @FXML
    protected void handleChooseAttdendingMachineAction() {
        if (attendingMachineIds.getValue() == null) {
            System.out.println("not choose machine yet");
        }

        if (attedanceRecordFile != null) {
            System.out.println("can handle retrieve");
            handleRetrieve();
        }
    }

    @FXML
    protected void handleTransformDataBtnAction() {
        resultTitle.setText("Processing data ...");
        tableSpace.getChildren().removeAll(tableRecord);
        showTransformDataTable(getTableData());
        resultTitle.setText("Transformed Data Result");
        transformRecordBtn.setDisable(true);
        saveAttendanceDataBtn.setDisable(false);
    }

    private void handleRetrieve() {
        tableSpace.getChildren().clear();
        resultTitle.setVisible(true);
        resultTitle.setText("Processing data ...");
        showRecordTable(getTableRecordData());
        resultTitle.setText("Records Result");
        transformRecordBtn.setVisible(true);
        transformRecordBtn.setDisable(false);
        saveAttendanceDataBtn.setVisible(true);
        saveAttendanceDataBtn.setDisable(true);
    }

    private void showRecordTable(List<AttendanceRecord> records) {
        tableRecord.getColumns().clear();
        tableRecord.getItems().clear();
        TableColumn employeeID = new TableColumn("EmployeeID");
        TableColumn timestamp = new TableColumn("Timestamp");

        tableRecord.getColumns().addAll(employeeID, timestamp);

        tableSpace.getChildren().addAll(tableRecord);
    }

    private void showTransformDataTable(Tuple2<OfficerAttendanceData, WorkerAttendanceData> dataTuple2) {
        if (dataTuple2.getWorkerAttendanceDataList() != null) {
            tableWorker.getColumns().clear();
            tableWorker.getItems().clear();
            TableColumn worker = new TableColumn("Worker");
            TableColumn date = new TableColumn("Date");
            TableColumn shift1 = new TableColumn("Shift 1");
            TableColumn shift2 = new TableColumn("Shift 2");
            TableColumn shift3 = new TableColumn("Shift 3");

            tableWorker.getColumns().addAll(worker, date, shift1, shift2, shift3);
            tableSpace.getChildren().add(tableWorker);
        }

        if (dataTuple2.getOfficerAttendanceDataList() != null) {
            tableOfficer.getColumns().clear();
            tableOfficer.getItems().clear();
            TableColumn officer = new TableColumn("Officer");
            TableColumn date = new TableColumn("Date");
            TableColumn morningSession = new TableColumn("Morning Session");
            TableColumn afternoonSession = new TableColumn("Afternoon Session");
            TableColumn hoursLate = new TableColumn("Hours Late");
            TableColumn hoursEarlyLeave = new TableColumn("Hours Early Leave");

            tableOfficer.getColumns().addAll(officer, date, morningSession, afternoonSession, hoursLate, hoursEarlyLeave);
            tableSpace.getChildren().add(tableOfficer);
        }
    }

    private Tuple2<OfficerAttendanceData, WorkerAttendanceData> getTableData() {
        // retrieve data
        // mock
        List<WorkerAttendanceData> workerAttendanceDataList = List.of(new WorkerAttendanceData(1, 1, new Date(), 1.0, 1.0, 1.0));
        List<OfficerAttendanceData> officerAttendanceDataList = List.of(new OfficerAttendanceData(1, 1, new Date(), true, true, 1.0, 1.0));

        Tuple2<OfficerAttendanceData, WorkerAttendanceData> data = new Tuple2<>(officerAttendanceDataList, workerAttendanceDataList);

        return data;
    }

    private List<AttendanceRecord> getTableRecordData() {
        // call controller -> service -> take data back

        return null;
    }
}
