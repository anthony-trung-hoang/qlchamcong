package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySQLEmployeeRepository implements IEmployeeRepository {
    private static Connection connection;

    public MySQLEmployeeRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void themEmployee(Employee item) {

    }

    @Override
    public void xoaEmployee(Employee item) {

    }

    @Override
    public void suaEmployee(Employee item) {

    }

    @Override
    public void capNhatEmployee(Employee item) {

    }

    @Override
    public List<Employee> layTatCaEmployee() {
        return null;
    }

    @Override
    public Employee timKiemTheoTen(String name) {
        return null;
    }

    @Override
    public Employee timKiemTheoTenDangNhap(String username) {
        return null;
    }

    @Override
    public String getRoleById(int employeeId) {
        String role = null;
        String query = "SELECT role FROM Employee WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    role = resultSet.getString("role");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }
}
