package com.example.qlchamcong.HRSystem;


import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.Employee;

import java.util.Date;
import java.util.List;

public interface IHRSystemAPIService {
    List<Department> getDeparmentList();
    List<Employee> getEmployeeList();
    List<Employee> getEmployeeList(int id, Date date);
}
