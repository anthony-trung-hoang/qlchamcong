package com.example.qlchamcong.repository;

import com.example.qlchamcong.database.DatabaseType;
import com.example.qlchamcong.repository.attendancerecord.IAttendanceRecordRepository;
import com.example.qlchamcong.repository.attendancerecord.MySQLAttendanceRecordRepository;
import com.example.qlchamcong.repository.employee.IEmployeeRepository;
import com.example.qlchamcong.repository.employee.MySQLEmployeeRepository;
import com.example.qlchamcong.repository.officerattendancedata.IOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.officerattendancedata.MySQLOfficerAttendanceDataRepository;
import com.example.qlchamcong.repository.timekeeper.ITimekeeperRepository;
import com.example.qlchamcong.repository.timekeeper.MySQLTimekeeperRepository;
import com.example.qlchamcong.repository.workerattendancedata.IWorkerAttendanceDataRepository;
import com.example.qlchamcong.repository.workerattendancedata.MySQLWorkerAttendanceDataRepository;

import java.sql.Connection;

public class RepositoryInitializer {
    private static IEmployeeRepository employeeRepository;
    private static IWorkerAttendanceDataRepository workerAttendanceDataRepository;
    private static IAttendanceRecordRepository attendanceRecordRepository;
    private static ITimekeeperRepository timekeeperRepository;
    private static IOfficerAttendanceDataRepository officerAttendanceDataRepository;
    public RepositoryInitializer(Connection connection, DatabaseType databaseType) {
        switch (databaseType) {
            case MYSQL:
                employeeRepository = new MySQLEmployeeRepository(connection);
                workerAttendanceDataRepository = new MySQLWorkerAttendanceDataRepository(connection);
                attendanceRecordRepository = new MySQLAttendanceRecordRepository(connection);
                timekeeperRepository = new MySQLTimekeeperRepository(connection);
                officerAttendanceDataRepository = new MySQLOfficerAttendanceDataRepository(connection);
        }
    }

    public static IEmployeeRepository getNguoiDungRepository() {
        return employeeRepository;
    }
    public static IWorkerAttendanceDataRepository getWorkerAttendanceDataRepository() {
        return workerAttendanceDataRepository;
    }

    public static IEmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public static ITimekeeperRepository getTimekeeperRepository() {
        return timekeeperRepository;
    }

    public static IOfficerAttendanceDataRepository getOfficerAttendanceDataRepository() {
        return officerAttendanceDataRepository;
    }

    public static IAttendanceRecordRepository getAttendanceRecordRepository() {
        return attendanceRecordRepository;
    }
}
