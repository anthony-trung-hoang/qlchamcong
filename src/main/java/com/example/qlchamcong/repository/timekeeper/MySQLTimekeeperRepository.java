package com.example.qlchamcong.repository.timekeeper;

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

        String sql = "SELECT id, type, timekeeperCode FROM timekeeper";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String timeKeeperCode = resultSet.getString("timekeeperCode");

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

        return null;
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

    @Override
    public boolean checkTimeKeeperCodeExists(String timeKeeperCode) {
        String query = "SELECT COUNT(*) FROM Timekeeper WHERE timeKeeperCode = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, timeKeeperCode);

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

    public List<Timekeeper> getTimekeepersByType(String type) {
        List<Timekeeper> timekeepers = new ArrayList<>();

        String sql = "SELECT id, type, timekeeperCode FROM timekeeper WHERE type = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, type);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String timeKeeperType = resultSet.getString("type");
                    String timeKeeperCode = resultSet.getString("timekeeperCode");

                    Timekeeper timekeeper = new Timekeeper(id, timeKeeperCode, timeKeeperType);
                    timekeepers.add(timekeeper);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return timekeepers;
    }

    @Override
    public int getTimekeepersByCode(String code) {
        String query = "SELECT id FROM Timekeeper WHERE timeKeeperCode = ?";
        int id = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, code);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}
