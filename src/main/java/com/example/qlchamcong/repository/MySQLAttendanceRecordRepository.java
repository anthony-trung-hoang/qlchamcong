package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.AttendanceRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAttendanceRecordRepository implements IAttendanceRecordRepository{
    private final Connection connection;

    public MySQLAttendanceRecordRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecordsByEmployeeAndDate(int employeeId, Date date) {
        List<AttendanceRecord> records = new ArrayList<>();
        String query = "SELECT ar.id, ar.employeeId, ar.timeKeeperId, ar.timestamp, tk.type " +
                "FROM AttendanceRecord ar " +
                "INNER JOIN Timekeeper tk ON ar.timeKeeperId = tk.id " +
                "WHERE ar.employeeId = ? AND DATE(ar.timestamp) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDate(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    AttendanceRecord record = new AttendanceRecord();
                    record.setId(resultSet.getInt("id"));
                    record.setEmployeeId(resultSet.getInt("employeeId"));
                    record.setTimeKeeperId(resultSet.getInt("timeKeeperId"));
                    record.setTimestamp(resultSet.getTimestamp("timestamp"));
                    record.setType(resultSet.getString("type"));  // Thêm trường type
                    records.add(record);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    @Override
    public void deleteRecordById(int id) {
        String query = "DELETE FROM AttendanceRecord WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
