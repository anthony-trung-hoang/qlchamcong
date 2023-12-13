package com.example.qlchamcong.repository;

import com.example.qlchamcong.database.DatabaseType;

import java.sql.Connection;

public class RepositoryInitializer {
    private static INguoiDungRepository nguoiDungRepository;
    private static IWorkerAttendanceDataRepository workerAttendanceDataRepository;
    public RepositoryInitializer(Connection connection, DatabaseType databaseType) {
        switch (databaseType) {
            case MYSQL:
                nguoiDungRepository = new MySQLNguoiDungRepository(connection);
                workerAttendanceDataRepository = new MySQLWorkerAttendanceDataRepository();
        }
    }

    public static INguoiDungRepository getNguoiDungRepository() {
        return nguoiDungRepository;
    }
    public static IWorkerAttendanceDataRepository getWorkerAttendanceDataRepository() {
        return workerAttendanceDataRepository;
    }
}
