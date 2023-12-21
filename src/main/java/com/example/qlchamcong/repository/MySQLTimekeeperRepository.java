package com.example.qlchamcong.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLTimekeeperRepository implements ITimekeeperRepository {
    private final Connection connection;

    public MySQLTimekeeperRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean checkTimeKeeperIdExists(int timeKeeperId) {
        String query = "SELECT COUNT(*) FROM Timekeeper WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, timeKeeperId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
