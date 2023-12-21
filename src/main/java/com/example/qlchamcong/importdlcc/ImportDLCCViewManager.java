package com.example.qlchamcong.importdlcc;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.Tuple2;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.exception.ConflictSavedAttendanceRecord;
import com.example.qlchamcong.exception.InvalidFileFormatException;
import com.example.qlchamcong.exception.MoreThanTwoRoleInRecords;
import com.example.qlchamcong.exception.TransformException;
import com.example.qlchamcong.service.ServiceInitializer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.*;

public class ImportDLCCViewManager implements Initializable {

    @FXML
    private TextField filePathCheckIn;
    @FXML
    private TextField filePathCheckout;
    @FXML
    private Button chooseFileCheckInBtn;
    @FXML
    private Button chooseFileCheckOutBtn;
    @FXML
    private ComboBox<String> timekeeperCheckInCodes;
    @FXML
    private ComboBox<String> timekeeperCheckOutCodes;
    @FXML
    private Label resultTitle;
    @FXML
    private VBox tableSpace;
    @FXML
    private Button saveAttendanceDataBtn;
    @FXML
    private Button transformRecordBtn;

    private ImportDLCCController importDLCCController;
    private File attedanceRecordCheckInFile;
    private File attedanceRecordCheckOutFile;
    private List<AttendanceRecord> attendanceRecordList;
    private Tuple2<WorkerAttendanceData, OfficerAttendanceData> transformedData;
    private TableView tableRecord = new TableView();
    private TableView tableWorker = new TableView();
    private TableView tableOfficer = new TableView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        importDLCCController = new ImportDLCCController(ServiceInitializer.getImportDLCCService());

        filePathCheckIn.setDisable(true);
        filePathCheckout.setDisable(true);
        timekeeperCheckInCodes.getItems().addAll(getTimekeeperCheckInCodes());
        timekeeperCheckOutCodes.getItems().addAll(getTimekeeperCheckOutCodes());
        resultTitle.setVisible(false);
        saveAttendanceDataBtn.setVisible(false);
        transformRecordBtn.setVisible(false);
    }

    @FXML
    protected void handleChooseFileCheckInBtnAction() {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) chooseFileCheckInBtn.getScene().getWindow();
        try {
            attedanceRecordCheckInFile = fileChooser.showOpenDialog(stage);
        } catch (Exception ex) {
            resetPage();
            showErrorAlert("Choose File", "Please choose a file");
        }
        if (attedanceRecordCheckInFile != null) {
            filePathCheckIn.setText(attedanceRecordCheckInFile.getPath());

            if (timekeeperCheckInCodes.getValue() == null) return;
            if (timekeeperCheckOutCodes.getValue() == null) return;
            if (attedanceRecordCheckOutFile == null) return;

            handleRetrieve();
        }
    }

    @FXML
    protected void handleChooseFileCheckOutBtnAction() {
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) chooseFileCheckOutBtn.getScene().getWindow();
        try {
            attedanceRecordCheckOutFile = fileChooser.showOpenDialog(stage);
        } catch (Exception ex) {
            resetPage();
            showErrorAlert("Choose File", "Please choose a file");
        }
        if (attedanceRecordCheckOutFile != null) {
            filePathCheckout.setText(attedanceRecordCheckOutFile.getPath());

            if (timekeeperCheckInCodes.getValue() == null) return;
            if (timekeeperCheckOutCodes.getValue() == null) return;
            if (attedanceRecordCheckInFile == null) return;

            handleRetrieve();
        }

    }

    @FXML
    protected void handleChooseTimekeeperCheckInAction() {
        if (timekeeperCheckInCodes.getValue() == null) {
            // alert
        }

        if (attedanceRecordCheckInFile == null) return;
        if (attedanceRecordCheckOutFile == null) return;
        if (timekeeperCheckOutCodes.getValue() == null) return;

        handleRetrieve();
    }

    @FXML
    protected void handleChooseTimekeeperCheckOutAction() {
        if (timekeeperCheckOutCodes.getValue() == null) {
//            System.out.println("not choose machine yet");
        }

        if (attedanceRecordCheckInFile == null) return;
        if (attedanceRecordCheckOutFile == null) return;
        if (timekeeperCheckInCodes.getValue() == null) return;

        handleRetrieve();
    }

    @FXML
    protected void handleTransformDataBtnAction() {
        resultTitle.setText("Processing data ...");
        tableSpace.getChildren().removeAll(tableRecord);
        getTableTransformData();
        showTransformDataTable();
        resultTitle.setText("Transformed Data Result");
        transformRecordBtn.setDisable(true);
        saveAttendanceDataBtn.setDisable(false);
    }

    @FXML
    protected void handleSaveDataBtnAction() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận lưu dữ liệu");
        confirmAlert.setHeaderText("Bạn có chắc muốn lưu dữ liệu không?");
        confirmAlert.initModality(Modality.APPLICATION_MODAL);

        confirmAlert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);

        confirmAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                importDLCCController.saveAttendanceData(transformedData, attendanceRecordList);

                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Lưu thành công");
                successAlert.setHeaderText("Dữ liệu đã được lưu thành công.");
                successAlert.showAndWait();
            } else {
            }
        });
    }

    private void handleRetrieve() {
        tableSpace.getChildren().clear();
        resultTitle.setVisible(true);
        resultTitle.setText("Processing data ...");
        getTableRecordData();
        showRecordTable();
        resultTitle.setText("Records Result");
        transformRecordBtn.setVisible(true);
        transformRecordBtn.setDisable(false);
        saveAttendanceDataBtn.setVisible(true);
        saveAttendanceDataBtn.setDisable(true);
    }

    private void showRecordTable() {
        if (attendanceRecordList == null) return;
        tableRecord.getColumns().clear();
        tableRecord.getItems().clear();
        TableColumn<AttendanceRecord, Integer> employeeIDColumn = new TableColumn<>("EmployeeID");
        TableColumn<AttendanceRecord, Long> timestampColumn = new TableColumn<>("Timestamp");
        TableColumn<AttendanceRecord, String> typeColumn = new TableColumn<>("Type");

        employeeIDColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));

        timestampColumn.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        tableRecord.getColumns().addAll(employeeIDColumn, timestampColumn, typeColumn);

        ObservableList<AttendanceRecord> observableRecords = FXCollections.observableArrayList(attendanceRecordList);

        observableRecords.sort(Comparator
                .comparing(AttendanceRecord::getEmployeeId)
                .thenComparing(AttendanceRecord::getTimestamp));

        tableRecord.setItems(observableRecords);

        tableSpace.getChildren().addAll(tableRecord);
    }

    private void showTransformDataTable() {
        if (transformedData == null) return;
        if (transformedData.getWorkerAttendanceDataList() != null) {
            tableWorker.getColumns().clear();
            tableWorker.getItems().clear();
            TableColumn<WorkerAttendanceData, Integer> worker = new TableColumn("Worker");
            TableColumn<WorkerAttendanceData, String> date = new TableColumn("Date");
            TableColumn<WorkerAttendanceData, Double> shift1 = new TableColumn("Shift 1");
            TableColumn<WorkerAttendanceData, Double> shift2 = new TableColumn("Shift 2");
            TableColumn<WorkerAttendanceData, Double> shift3 = new TableColumn("Shift 3");

            worker.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            shift1.setCellValueFactory(new PropertyValueFactory<>("hoursShift1"));
            shift2.setCellValueFactory(new PropertyValueFactory<>("hoursShift2"));
            shift3.setCellValueFactory(new PropertyValueFactory<>("hoursShift3"));

            tableWorker.getColumns().addAll(worker, date, shift1, shift2, shift3);

            ObservableList<WorkerAttendanceData> observableRecords = FXCollections.observableArrayList(transformedData.getWorkerAttendanceDataList());

            tableWorker.setItems(observableRecords);

            tableSpace.getChildren().add(tableWorker);
        }

        if (transformedData.getOfficerAttendanceDataList() != null) {
            tableOfficer.getColumns().clear();
            tableOfficer.getItems().clear();
            TableColumn<OfficerAttendanceData, Integer> officer = new TableColumn("Officer");
            TableColumn<OfficerAttendanceData, String> date = new TableColumn("Date");
            TableColumn<OfficerAttendanceData, Boolean> morningSession = new TableColumn("Morning Session");
            TableColumn<OfficerAttendanceData, Boolean> afternoonSession = new TableColumn("Afternoon Session");
            TableColumn<OfficerAttendanceData, Double> hoursLate = new TableColumn("Hours Late");
            TableColumn<OfficerAttendanceData, Double> hoursEarlyLeave = new TableColumn("Hours Early Leave");

            officer.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            morningSession.setCellValueFactory(new PropertyValueFactory<>("morningSession"));
            afternoonSession.setCellValueFactory(new PropertyValueFactory<>("afternoonSession"));
            hoursLate.setCellValueFactory(new PropertyValueFactory<>("hoursLate"));
            hoursEarlyLeave.setCellValueFactory(new PropertyValueFactory<>("hoursEarlyLeave"));

            tableOfficer.getColumns().addAll(officer, date, morningSession, afternoonSession, hoursLate, hoursEarlyLeave);

            ObservableList<OfficerAttendanceData> observableRecords = FXCollections.observableArrayList(transformedData.getOfficerAttendanceDataList());

            tableOfficer.setItems(observableRecords);

            tableSpace.getChildren().add(tableOfficer);
        }
    }

    private void getTableTransformData() {
        try {
            transformedData = importDLCCController.getTransformedData(attendanceRecordList);
        } catch (TransformException e) {
            resetPage();
            showErrorAlert("Invalid", e.getMessage());
        }
    }

    private void getTableRecordData() {
        // call controller -> service -> take data back
        try {
            attendanceRecordList = importDLCCController.getAttendanceRecord(attedanceRecordCheckInFile, timekeeperCheckInCodes.getValue(), attedanceRecordCheckOutFile, timekeeperCheckOutCodes.getValue());
        } catch (InvalidFileFormatException | ConflictSavedAttendanceRecord e) {
            showErrorAlert("Invalid", e.getMessage());
            resetPage();
        }
    }

    private List<String> getTimekeeperCheckInCodes() {
        return importDLCCController.getAllTimekeeperCheckInCode();
    }

    private List<String> getTimekeeperCheckOutCodes() {
        return importDLCCController.getAllTimekeeperCheckOutCode();
    }

    private void showErrorAlert(String title, String content) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(content);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
            }
        });
    }

    private void resetPage() {
        tableSpace.getChildren().clear();
        resultTitle.setVisible(false);
        transformRecordBtn.setVisible(false);
        saveAttendanceDataBtn.setVisible(false);
        filePathCheckIn.clear();
        filePathCheckout.clear();
        attendanceRecordList = null;
        attedanceRecordCheckInFile = null;
        attedanceRecordCheckOutFile = null;
    }
}
