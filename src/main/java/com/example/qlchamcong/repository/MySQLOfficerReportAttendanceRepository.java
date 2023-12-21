package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.OfficerAttendanceData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLOfficerReportAttendanceRepository implements IOfficerReportAttendanceRepository{
    private final Connection connection;

    public MySQLOfficerReportAttendanceRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<OfficerAttendanceData> getOfficerReportAttendance(Date startDate, Date endDate, List<Integer> employeeIdList) {
        List<OfficerAttendanceData> dataList= new ArrayList<>();
        System.out.println(employeeIdList);
        for (Integer id:employeeIdList){
            String query = "SELECT * FROM WorkerAttendanceData WHERE date BETWEEN '2023-01-01' AND '2023-01-01' AND employeeId = id";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
