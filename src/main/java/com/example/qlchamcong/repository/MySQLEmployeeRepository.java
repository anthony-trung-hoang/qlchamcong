package com.example.qlchamcong.repository;

import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.entity.Role;

import java.sql.Connection;
import java.util.ArrayList;
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
        var query = "SELECT * FROM employee limit 10";
        List<Employee> employees = new ArrayList<>();

        try {
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                var employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("username"));
                employee.setRole(Role.valueOf(resultSet.getString("role")));
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public Employee timKiemTheoTen(String name) {
        return null;
    }

    @Override
    public Employee timKiemTheoTenDangNhap(String username) {
        return null;
    }
}
