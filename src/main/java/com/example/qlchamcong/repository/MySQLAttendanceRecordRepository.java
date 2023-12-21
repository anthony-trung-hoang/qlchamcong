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
        String query = "SELECT ar.id, ar.employeeId, ar.timeKeeperId, ar.timestamp, tk.type, tk.timeKeeperCode " +
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
                    record.setType(resultSet.getString("type"));
                    record.setTimeKeeperCode(resultSet.getString("timeKeeperCode"));
                    records.add(record);

                    System.out.println(record);
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

    @Override
    public void createANewRecord(AttendanceRecord newRecord) {
        String query = "INSERT INTO AttendanceRecord (employeeId, timeKeeperId, timestamp) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, newRecord.getEmployeeId());
            preparedStatement.setInt(2, newRecord.getTimeKeeperId());
            preparedStatement.setTimestamp(3, newRecord.getTimestamp());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating record failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newRecord.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating record failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRecordById(AttendanceRecord updatedRecord) {
        String query = "UPDATE AttendanceRecord SET timestamp = ?, timeKeeperId = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, updatedRecord.getTimestamp());
            preparedStatement.setInt(2, updatedRecord.getTimeKeeperId());
            preparedStatement.setInt(3, updatedRecord.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNumberOfRecordsInADayByDateAndEmployee(int employeeId, Date date) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM AttendanceRecord WHERE employeeId = ? AND DATE(timestamp) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDate(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}
