package com.example.qlchamcong.repository.workerreportattendancereporitory;

import com.example.qlchamcong.entity.WorkerAttendanceData;
import com.example.qlchamcong.repository.workerreportattendancereporitory.IWorkerReportAttendanceRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLWorkerReportAttendanceRepository implements IWorkerReportAttendanceRepository {
    private final Connection connection;

    public MySQLWorkerReportAttendanceRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<WorkerAttendanceData> getWorkerReportAttendance(String startDate, String endDate, List<Integer> employeeIdList) {
        List<WorkerAttendanceData> dataList= new ArrayList<>();
        for (Integer id:employeeIdList){
            String query = "SELECT * FROM WorkerAttendanceData WHERE date BETWEEN ? AND ? AND employeeId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, startDate);
                preparedStatement.setString(2, endDate);
                preparedStatement.setInt(3, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int aId=resultSet.getInt("id");
                        int employeeId=resultSet.getInt("employeeId");
                        Date date= resultSet.getDate("date");
                        double hoursShift1= resultSet.getDouble("hoursShift1");
                        double hoursShift2=resultSet.getDouble("hoursShift2");
                        double hoursShift3=resultSet.getDouble("hoursShift3");
                        WorkerAttendanceData data = new WorkerAttendanceData(aId,employeeId,date,hoursShift1,hoursShift2,hoursShift3);
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
