package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.OfficerAttendanceData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String query = "UPDATE OfficerAttendanceData SET morningSession = ?, afternoonSession = ?, hoursLate = ?, hoursEarlyLeave = ? WHERE id = ?";

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

    @Override
    public List<OfficerAttendanceData> getAllOfficerAttendanceDataByDate(java.sql.Date date) {


        List<OfficerAttendanceData> dataList = new ArrayList<>();
        String query = "SELECT * FROM OfficerAttendanceData WHERE DATE(date) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, (java.sql.Date) date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OfficerAttendanceData data = new OfficerAttendanceData();
                    data.setId(resultSet.getInt("id"));
                    data.setEmployeeId(resultSet.getInt("employeeId"));
                    data.setDate(resultSet.getDate("date"));
                    data.setAfternoonSession(resultSet.getBoolean("afternoonSession"));
                    data.setMorningSession(resultSet.getBoolean("morningSession"));
                    data.setHoursEarlyLeave(resultSet.getDouble("hoursEarlyLeave"));
                    data.setHoursLate(resultSet.getDouble("hoursLate"));
                    dataList.add(data);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dataList;
    }
}
