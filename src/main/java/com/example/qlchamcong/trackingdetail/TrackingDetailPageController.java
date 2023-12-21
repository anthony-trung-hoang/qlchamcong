package com.example.qlchamcong.trackingdetail;

import com.example.qlchamcong.entity.Employee;
import com.example.qlchamcong.service.ITrackingService;

import java.util.List;

public class TrackingDetailPageController {
    private final ITrackingService trackingService;

    public TrackingDetailPageController(ITrackingService trackingService) {
        this.trackingService = trackingService;
    }

    public List<Employee> getEmployeeList() {
        return trackingService.getAllEmployee();
    }
}
