package com.example.qlchamcong.repository.workerattendancedata;

import com.example.qlchamcong.entity.WorkerAttendanceData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySQLWorkerAttendanceDataRepository implements IWorkerAttendanceDataRepository {

    private final Connection connection;

    public MySQLWorkerAttendanceDataRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<WorkerAttendanceData> getAllWorkerAttendanceDataByDate(Date date) {
        List<WorkerAttendanceData> dataList = new ArrayList<>();
        String query = "SELECT * FROM WorkerAttendanceData WHERE DATE(date) = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, (java.sql.Date) date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    WorkerAttendanceData data = new WorkerAttendanceData();
                    data.setId(resultSet.getInt("id"));
                    data.setEmployeeId(resultSet.getInt("employeeId"));
                    data.setDate(resultSet.getDate("date"));
                    data.setHoursShift1(resultSet.getDouble("hoursShift1"));
                    data.setHoursShift2(resultSet.getDouble("hoursShift2"));
                    data.setHoursShift3(resultSet.getDouble("hoursShift3"));
                    dataList.add(data);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dataList;
    }

    @Override
    public WorkerAttendanceData getWorkerAttendanceDataByEmployeeAndDate(int employeeId, Date date) {
        String query = "SELECT * FROM WorkerAttendanceData WHERE employeeId = ? AND date = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    WorkerAttendanceData workerAttendanceData = new WorkerAttendanceData();
                    workerAttendanceData.setId(resultSet.getInt("id"));
                    workerAttendanceData.setEmployeeId(resultSet.getInt("employeeId"));
                    workerAttendanceData.setDate(resultSet.getDate("date"));
                    workerAttendanceData.setHoursShift1(resultSet.getDouble("hoursShift1"));
                    workerAttendanceData.setHoursShift2(resultSet.getDouble("hoursShift2"));
                    workerAttendanceData.setHoursShift3(resultSet.getDouble("hoursShift3"));
                    return workerAttendanceData;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateWorkerAttendanceData(int id, double hoursShift1, double hoursShift2, double hoursShift3) {
        System.out.println(hoursShift1 + " " + hoursShift2 + hoursShift3);
        String query = "UPDATE WorkerAttendanceData SET hoursShift1 = ?, hoursShift2 = ?, hoursShift3 = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, hoursShift1);
            preparedStatement.setDouble(2, hoursShift2);
            preparedStatement.setDouble(3, hoursShift3);
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


//    @Override
//    public List<WorkerAttendanceData> getAllAttendanceData() {
//        workerAttendanceDataList.clear();
//        workerAttendanceDataList.add(createSampleObject(1, 101, new Date(), 8.0, 7.5, 6.5));
//        workerAttendanceDataList.add(createSampleObject(2, 102, new Date(), 7.0, 7.5, 8.0));
//        workerAttendanceDataList.add(createSampleObject(3, 103, new Date(), 6.0, 6.5, 7.5));
//        workerAttendanceDataList.add(createSampleObject(4, 104, new Date(), 8.5, 8.0, 7.0));
//        workerAttendanceDataList.add(createSampleObject(5, 105, new Date(), 7.5, 7.0, 6.0));
//
//        return this.workerAttendanceDataList;
//    }
//
//    //    mock data
//    private WorkerAttendanceData createSampleObject(int id, int staffId, Date workDate,
//                                                    double hoursForShift1, double hoursForShift2, double hoursForShift3) {
//        return new WorkerAttendanceData(id, staffId, workDate, hoursForShift1, hoursForShift2, hoursForShift3);
//    }

