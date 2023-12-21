package com.example.qlchamcong.qlnshome;

import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.entity.OfficerAttendanceData;
import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HRHomeViewManager implements Initializable {

    private HRHomeController qlnsHomeController;
    
    //    worker table
    @FXML
    private TableView<WorkerAttendanceData> attendanceTableView;

    @FXML
    private TableColumn<WorkerAttendanceData, Integer> idColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, Integer> employeeIdColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, String> dateColumn;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift1Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift2Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Double> hoursShift3Column;

    @FXML
    private TableColumn<WorkerAttendanceData, Void> viewDetailsColumn;

    // officer table 
    @FXML
    public TableView<OfficerAttendanceData> officerAttendanceTableView;

    @FXML
    public TableColumn<OfficerAttendanceData, Integer> oIdColumn;

    @FXML
    public TableColumn<OfficerAttendanceData, Integer> officerIdColumn;

    @FXML
    public TableColumn<OfficerAttendanceData, String> officerDateColumn;

    @FXML
    public TableColumn<OfficerAttendanceData, String> morningSessionColumn;

    @FXML
    public TableColumn<OfficerAttendanceData, String> afternoonSessionColumn;

    @FXML
    public  TableColumn<OfficerAttendanceData, Double> hoursLateColumn;

    @FXML
    public  TableColumn<OfficerAttendanceData, Double> earlyLeaveColumn;

    @FXML
    public TableColumn<OfficerAttendanceData, Void> viewOfficerDetailsColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        qlnsHomeController = new HRHomeController(navUtil, argumentUtil);

        fetchAndDisplayWorkerTableData();
        fetchAndDisplayOfficerTableData();
    }

    public void fetchAndDisplayWorkerTableData() {
        idColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        employeeIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
        dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
        hoursShift1Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift1()).asObject());
        hoursShift2Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift2()).asObject());
        hoursShift3Column.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursShift3()).asObject());

        viewDetailsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button viewDetailsButton = new Button("Xem Chi Tiết");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(viewDetailsButton);
                    viewDetailsButton.setOnAction(event -> {
                        try {
                            handleViewDetailsAction(getTableView().getItems().get(getIndex()));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
        });

        List<WorkerAttendanceData> workerAttendanceDataList = qlnsHomeController.fetchTableWorkerData();
        System.out.println(Arrays.toString(workerAttendanceDataList.toArray()));
        ObservableList<WorkerAttendanceData> observableList = FXCollections.observableArrayList(workerAttendanceDataList);
        attendanceTableView.setItems(observableList);
    }

    public void fetchAndDisplayOfficerTableData() {
        try {
            oIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
            officerIdColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
            officerDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate().toString()));
            morningSessionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isMorningSession() ? "Yes" : "No"));
            afternoonSessionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAfternoonSession() ? "Yes" : "No"));
            hoursLateColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursLate()).asObject());
            earlyLeaveColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getHoursEarlyLeave()).asObject());

            viewOfficerDetailsColumn.setCellFactory(param -> new TableCell<>() {
                private final Button viewOfficerDetailsButton = new Button("Xem Chi Tiết");

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(viewOfficerDetailsButton);
                        viewOfficerDetailsButton.setOnAction(event -> {
                            try {
                                handleViewOfficerDetailsAction(getTableView().getItems().get(getIndex()));
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            });

            List<OfficerAttendanceData> officerAttendanceData = qlnsHomeController.fetchTableOfficerData();
            System.out.println(Arrays.toString(officerAttendanceData.toArray()));
            ObservableList<OfficerAttendanceData> observableList = FXCollections.observableArrayList(officerAttendanceData);
            officerAttendanceTableView.setItems(observableList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void handleViewDetailsAction(WorkerAttendanceData workerAttendanceData) throws IOException {
        qlnsHomeController.showDetails(workerAttendanceData);
    }
    private void handleViewOfficerDetailsAction(OfficerAttendanceData officerAttendanceData) throws IOException {
        qlnsHomeController.showDetails(officerAttendanceData);
    }
}
