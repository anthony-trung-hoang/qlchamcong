package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.OfficerAttendanceData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class MySQLOfficerAttendanceDataRepository implements IOfficerAttendanceDataRepository {
    private final Connection connection;
    public MySQLOfficerAttendanceDataRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OfficerAttendanceData getOfficerAttendanceDataByEmployeeAndDate(int employeeId, Date date) {
        String query = "SELECT * FROM OfficerAttendanceData WHERE employeeId = ? AND date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    OfficerAttendanceData officerAttendanceData = new OfficerAttendanceData();
                    officerAttendanceData.setId(resultSet.getInt("id"));
                    officerAttendanceData.setEmployeeId(resultSet.getInt("employeeId"));
                    officerAttendanceData.setDate(resultSet.getDate("date"));
                    officerAttendanceData.setMorningSession(resultSet.getBoolean("morningSession"));
                    officerAttendanceData.setAfternoonSession(resultSet.getBoolean("afternoonSession"));
                    officerAttendanceData.setHoursLate(resultSet.getDouble("hoursLate"));
                    officerAttendanceData.setHoursEarlyLeave(resultSet.getDouble("hoursEarlyLeave"));
                    return officerAttendanceData;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateOfficerAttendanceData(int id, boolean morningSession, boolean afternoonSession, double lateHours, double earlyLeaveHours) {
        String query = "UPDATE OfficerAttendanceData SET morning_session = ?, afternoon_session = ?, late_hours = ?, early_leave_hours = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBoolean(1, morningSession);
            preparedStatement.setBoolean(2, afternoonSession);
            preparedStatement.setDouble(3, lateHours);
            preparedStatement.setDouble(4, earlyLeaveHours);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
