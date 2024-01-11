package com.example.qlchamcong.repository.officerreportattendancerepositorry;

import com.example.qlchamcong.entity.OfficerAttendanceData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLOfficerReportAttendanceRepository implements IOfficerReportAttendanceRepository {
    private final Connection connection;

    public MySQLOfficerReportAttendanceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OfficerAttendanceData> getOfficerReportAttendance(String startDate, String endDate, List<Integer> employeeIdList) {
        List<OfficerAttendanceData> dataList= new ArrayList<>();
        for (Integer id:employeeIdList){
            String query = "SELECT * FROM OfficerAttendanceData WHERE date BETWEEN ? AND ? AND employeeId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                System.out.println(startDate);
                System.out.println(endDate);
                preparedStatement.setString(1, startDate);
                preparedStatement.setString(2, endDate);
                preparedStatement.setInt(3, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int aId=resultSet.getInt("id");
                        int employeeId=resultSet.getInt("employeeId");
                        Date date= resultSet.getDate("date");
                        boolean morningSession= resultSet.getBoolean("morningSession");
                        boolean afternoonSession=resultSet.getBoolean("afternoonSession");
                        double hoursLate=resultSet.getDouble("hoursLate");
                        double hoursEarlyLeave=resultSet.getDouble("hoursEarlyLeave");
                        OfficerAttendanceData data = new OfficerAttendanceData(aId,employeeId,date,morningSession,afternoonSession,hoursLate,hoursEarlyLeave);
                        dataList.add(data);
                        System.out.println(data.getEmployeeId());
                    }
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return dataList;
    }
}
