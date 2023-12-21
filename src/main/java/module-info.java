module com.example.qlchamcong {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.google.gson;
    requires com.opencsv;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.example.qlchamcong to javafx.fxml;
    opens com.example.qlchamcong.dangnhap to javafx.fxml;
    opens com.example.qlchamcong.hrnavbar to javafx.fxml;
    opens com.example.qlchamcong.importdlcc to javafx.fxml;
    opens com.example.qlchamcong.qlnshome to javafx.fxml;
    opens com.example.qlchamcong.viewattendancerecord to javafx.fxml;
    opens com.example.qlchamcong.service to javafx.fxml;
    opens com.example.qlchamcong.changeGUIUtility to javafx.fxml;
    opens com.example.qlchamcong.viewattendancerecord.updaterecord to javafx.fxml;
    opens com.example.qlchamcong.repository to javafx.fxml;
    opens com.example.qlchamcong.viewattendancerecord.addrecord to javafx.fxml;
    opens com.example.qlchamcong.viewattendancerecord.deleterecord to javafx.fxml;
    opens com.example.qlchamcong.thanhdieuhuongtdv to javafx.fxml;
    opens com.example.qlchamcong.truongdonvihome to javafx.fxml;

    opens com.example.qlchamcong.viewreportfactoryattendance to javafx.fxml;
    opens com.example.qlchamcong.viewreportofficeattendance to javafx.fxml;
    opens com.example.qlchamcong.viewdepartmentlist to javafx.fxml;

    exports com.example.qlchamcong.viewreportfactoryattendance;
    exports com.example.qlchamcong.viewreportofficeattendance;
    exports com.example.qlchamcong.viewdepartmentlist;

    exports com.example.qlchamcong.viewattendancerecord;
    exports com.example.qlchamcong;
    exports com.example.qlchamcong.dangnhap;
    exports com.example.qlchamcong.hrnavbar;
    exports com.example.qlchamcong.importdlcc;
    exports com.example.qlchamcong.qlnshome;
    exports com.example.qlchamcong.service;
    exports com.example.qlchamcong.HRSystem.entity;
    exports com.example.qlchamcong.HRSystem;
    exports com.example.qlchamcong.entity;
    exports com.example.qlchamcong.repository;
    exports com.example.qlchamcong.changeGUIUtility;
    exports com.example.qlchamcong.viewattendancerecord.updaterecord;
    exports com.example.qlchamcong.viewattendancerecord.addrecord;
    exports com.example.qlchamcong.viewattendancerecord.deleterecord;
    exports com.example.qlchamcong.thanhdieuhuongtdv;
    exports com.example.qlchamcong.truongdonvihome;
    exports com.example.qlchamcong.passaargumentutility;
    opens com.example.qlchamcong.passaargumentutility to javafx.fxml;
    exports com.example.qlchamcong.service.reportattendanceservice;
    opens com.example.qlchamcong.service.reportattendanceservice to javafx.fxml;
    exports com.example.qlchamcong.repository.reportattendancerepositorry;
    opens com.example.qlchamcong.repository.reportattendancerepositorry to javafx.fxml;
    exports com.example.qlchamcong.repository.workerreportattendancereporitory;
    opens com.example.qlchamcong.repository.workerreportattendancereporitory to javafx.fxml;
}