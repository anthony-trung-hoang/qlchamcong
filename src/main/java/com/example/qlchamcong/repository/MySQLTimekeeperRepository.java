package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Timekeeper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTimekeeperRepository implements ITimekeeperRepository {
    private final Connection connection;
    public MySQLTimekeeperRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Timekeeper> getAllTimekeeper() {
        List<Timekeeper> timekeepers = new ArrayList<>();

        // Tạo câu truy vấn SQL
        String sql = "SELECT id, type, timekeeperCode FROM timekeeper";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Lặp qua các hàng kết quả và thêm chúng vào danh sách
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String timeKeeperCode = resultSet.getString("timekeeperCode");

                // Tạo đối tượng Timekeeper và thêm vào danh sách
                Timekeeper timekeeper = new Timekeeper(id, timeKeeperCode, type);
                timekeepers.add(timekeeper);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return timekeepers;
    }

    public Timekeeper getTimekeeperByCode(String timeKeeperCode) {
        try {
            // Tạo câu truy vấn SQL với điều kiện WHERE để lấy máy chấm công cụ thể
            String sql = "SELECT id, type, timekeeperCode FROM timekeeper WHERE timekeeperCode = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, timeKeeperCode);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String type = resultSet.getString("type");
                        String code = resultSet.getString("timekeeperCode");

                        return new Timekeeper(id, code, type);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Trả về null nếu không tìm thấy
    }
}
