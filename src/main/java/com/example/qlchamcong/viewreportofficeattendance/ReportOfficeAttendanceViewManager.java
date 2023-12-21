package com.example.qlchamcong.viewreportofficeattendance;

import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.NavigationUtil;
import com.example.qlchamcong.entity.OfficerReportAttendance;
import com.example.qlchamcong.passaargumentutility.IPassArgument;
import com.example.qlchamcong.passaargumentutility.PassArgumentUtil;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.text.ParseException;
import java.time.Month;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ReportOfficeAttendanceViewManager implements Initializable {
    public TableView<OfficerReportAttendance> reportAttendanceTableView;
    public TableColumn<OfficerReportAttendance, Integer> employeeId;
    public TableColumn<OfficerReportAttendance, String> employeeName;
    public TableColumn<OfficerReportAttendance, String> departmentName;
    public TableColumn<OfficerReportAttendance, String> month;
    public TableColumn<OfficerReportAttendance, Integer> totalWorkSession;
    public TableColumn<OfficerReportAttendance, Double> totalHoursLate;
    public TableColumn<OfficerReportAttendance, Double> totalHoursLeavingEarly;
    public ComboBox<String> monthReport;
    public ComboBox<Integer> yearReport;

    private ReportOfficeAttendanceController reportOfficeAttendanceController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        IActionChangeGUI navUtil = new NavigationUtil();
        IPassArgument argumentUtil = new PassArgumentUtil();
        reportOfficeAttendanceController = new ReportOfficeAttendanceController(navUtil, argumentUtil);
        Department departmentCurrent = getInitialData();
        displayDatePicker();
            fetchAndDisplayTableData(departmentCurrent.getId(),new Date());

    }

    private void fetchAndDisplayTableData(int id, Date date)  {
        employeeId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getEmployeeId()).asObject());
        employeeName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployeeName()));
        departmentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartmentName()));
        month.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMonth()));
        totalHoursLate.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalHoursLate()).asObject());
        totalWorkSession.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalWorkSession()).asObject());
        totalHoursLeavingEarly.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTotalHoursLeavingEarly()).asObject());
        List<OfficerReportAttendance> officerReportAttendanceList = reportOfficeAttendanceController.fetchListOfOfficerReportAttendance(id,date);

        ObservableList<OfficerReportAttendance> observableList = FXCollections.observableArrayList(officerReportAttendanceList);
        reportAttendanceTableView.setItems(observableList);
    }
    private void displayDatePicker(){
        ObservableList<String> months = FXCollections.observableArrayList(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
        monthReport.setItems(months);
        ObservableList<Integer> years = FXCollections.observableArrayList();
        int currentYear = java.time.Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 20; i--) {
            years.add(i);
        }
        yearReport.setItems(years);
        monthReport.setValue(String.valueOf(Month.values()[java.time.LocalDate.now().getMonthValue() - 1]));
        yearReport.setValue(currentYear);
        monthReport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {

            }
        });
        yearReport.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                System.out.println("Năm được chọn: " + newValue);
                // Gọi hàm xử lý hoặc thực hiện các hành động khác ở đây
            }
        });

    }
    public Department getInitialData() {
        return  reportOfficeAttendanceController.getInitialData();
    }

}
