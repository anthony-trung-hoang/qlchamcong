package com.example.qlchamcong.trackingdetail;

import com.example.qlchamcong.database.MySQLConnector;
import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.repository.IEmployeeRepository;
import com.example.qlchamcong.repository.MySQLEmployeeRepository;
import com.example.qlchamcong.service.ITrackingService;
import com.example.qlchamcong.service.TrackingService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class TrackingDetailPageView implements Initializable {
    private TrackingDetailPageController controller;

    @FXML
    private ComboBox<String> listEmployee;

    @FXML
    private DatePicker dateSearch;


    public TrackingDetailPageView() {
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MySQLConnector connector = new MySQLConnector();
        IEmployeeRepository employeeRepository = new MySQLEmployeeRepository(connector.getConnection());
        ITrackingService trackingService = new TrackingService(employeeRepository);
        this.controller = new TrackingDetailPageController(trackingService);
        listEmployee.getItems().addAll(controller.getEmployeeList().stream().map(Employee::getName).toList());
    }


    @FXML
    public void handleSearchButtonAction() {
        System.out.println(listEmployee.getValue());
        System.out.println(dateSearch.getValue());
    }
}
