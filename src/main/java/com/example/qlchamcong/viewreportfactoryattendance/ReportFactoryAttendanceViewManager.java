package com.example.qlchamcong.viewreportfactoryattendance;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.entity.WorkerReportAttendance;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import com.example.qlchamcong.viewreportofficeattendance.ReportOfficeAttendanceController;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReportFactoryAttendanceViewManager implements Initializable {
    public TableView<WorkerReportAttendance> reportAttendanceTableView;
    public TableColumn<WorkerReportAttendance, Integer> employeeId;
    public TableColumn<WorkerReportAttendance, String> employeeName;
    public TableColumn<WorkerReportAttendance, String> departmentName;
    public TableColumn<WorkerReportAttendance, String> month;
    public TableColumn<WorkerReportAttendance, Double> totalWork;
    public TableColumn<WorkerReportAttendance, Double> totalOvertime;
    public ComboBox<String> monthReport;
    public ComboBox<Integer> yearReport;

    private ReportFactoryAttendanceController reportFactoryAttendanceController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        reportFactoryAttendanceController = new ReportFactoryAttendanceController(navUtil, argumentUtil);
        Department departmentCurrent = getInitialData();
        displayDatePicker(departmentCurrent.getId());
        fetchAndDisplayTableData(departmentCurrent.getId());
    }

    private void fetchAndDisplayTableData(int id)  {
        employeeId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
        employeeName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeName()));
        departmentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartmentName()));
        month.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        totalWork.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalWork()).asObject());
        totalOvertime.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalOvertime()).asObject());
        List<WorkerReportAttendance> officerReportAttendanceList = reportFactoryAttendanceController.fetchListOfWorkerReportAttendance(id,monthReport.getValue(), String.valueOf(yearReport.getValue()));

        ObservableList<WorkerReportAttendance> observableList = FXCollections.observableArrayList(officerReportAttendanceList);
        reportAttendanceTableView.setItems(observableList);
    }
    private void displayDatePicker(int id){
        ObservableList<String> months = FXCollections.observableArrayList(
                "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "10", "11", "12"
        );
        monthReport.setItems(months);
        ObservableList<Integer> years = FXCollections.observableArrayList();
        int currentYear = java.time.Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 20; i--) {
            years.add(i);
        }
        yearReport.setItems(years);
        monthReport.setValue(String.valueOf(java.time.LocalDate.now().getMonthValue()));
        yearReport.setValue(currentYear);
        monthReport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                fetchAndDisplayTableData(id);
            }
        });
        yearReport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                fetchAndDisplayTableData(id);
            }
        });

    }
    public Department getInitialData() {
        return  reportFactoryAttendanceController.getInitialData();
    }
}
