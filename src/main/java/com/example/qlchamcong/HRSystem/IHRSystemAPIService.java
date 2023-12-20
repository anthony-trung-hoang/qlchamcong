package com.example.qlchamcong.HRSystem;


import com.example.qlchamcong.HRSystem.entity.Department;
import com.example.qlchamcong.HRSystem.entity.Employee;

import java.util.List;

public interface IHRSystemAPIService {
    List<Department> getDeparmentList();
    List<Employee> getEmployeeList();
}
