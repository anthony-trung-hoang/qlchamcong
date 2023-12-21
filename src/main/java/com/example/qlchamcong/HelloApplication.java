package com.example.qlchamcong;

import com.example.qlchamcong.HRSystem.HRSystemInitializer;
import com.example.qlchamcong.dangnhap.DangNhapViewManager;
import com.example.qlchamcong.database.DatabaseType;
import com.example.qlchamcong.database.MySQLConnector;
import com.example.qlchamcong.repository.RepositoryInitializer;
import com.example.qlchamcong.service.ServiceInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = null;

        if (Config.DATABASE_TYPE == DatabaseType.MYSQL) {
            connection = (new MySQLConnector()).getConnection();
        }

        new HRSystemInitializer();
        new RepositoryInitializer(connection, Config.DATABASE_TYPE);
        new ServiceInitializer();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/qlchamcong/dangnhap/dang-nhap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Phan mem quan ly cham cong");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}