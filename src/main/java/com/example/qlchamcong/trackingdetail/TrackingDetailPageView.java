package com.example.qlchamcong.trackingdetail;

import com.example.qlchamcong.database.MySQLConnector;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.repository.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.IEmployeeRepository;
import com.example.qlchamcong.repository.MySQLAttendanceRecordRepository;
import com.example.qlchamcong.repository.MySQLEmployeeRepository;
import com.example.qlchamcong.service.ITrackingService;
import com.example.qlchamcong.service.TrackingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class TrackingDetailPageView implements Initializable {
    private TrackingDetailPageController controller;

    @FXML
    private ComboBox<Employee> listEmployee;

    @FXML
    private DatePicker dateSearch;


    public TrackingDetailPageView() {
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySQLConnector connector = new MySQLConnector();
        IEmployeeRepository employeeRepository = new MySQLEmployeeRepository(connector.getConnection());
        IAttendanceRecordRepository attendanceRecordRepository = new MySQLAttendanceRecordRepository(connector.getConnection());
        ITrackingService trackingService = new TrackingService(employeeRepository, attendanceRecordRepository);
        this.controller = new TrackingDetailPageController(trackingService);
        listEmployee.getItems().addAll(controller.getEmployeeList());
        listEmployee.setConverter(new StringConverter<Employee>() {
            @Override
            public String toString(Employee employee) {
                return employee.getName();
            }

            @Override
            public Employee fromString(String s) {
                return listEmployee.getSelectionModel().getSelectedItem();
            }
        });


    }


    @FXML
    public void handleSearchButtonAction() {
        System.out.println(listEmployee.getValue().getId());
        System.out.println(dateSearch.getValue());
    }
}
