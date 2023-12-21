package com.example.qlchamcong.dangnhap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DangNhapViewManager implements Initializable {
    private IDangNhapController controller;

    @FXML
    protected Button dangNhap;
    @FXML
    protected TextField tenDangNhap;
    @FXML
    protected PasswordField matKhau;

    @FXML
    protected void onDangNhapButtonClick() throws IOException {
//        String result = controller.xulylogicnheae

        String role = tenDangNhap.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/qlchamcong/hrnavbar/hr-navbar.fxml"));
        if (Objects.equals(role, "1")) {
//            System.out.println(role);

            loader = new FXMLLoader(getClass().getResource("/com/example/qlchamcong/thanhdieuhuongtdv/thanh-dieu-huong-tdv.fxml"));
        }

        Stage stage = (Stage) dangNhap.getScene().getWindow();

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.centerOnScreen();
//        System.out.println("Dang nhap thanh cong");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller = new DangNhapController();
    }
}
