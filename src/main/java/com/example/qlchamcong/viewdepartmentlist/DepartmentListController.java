package com.example.qlchamcong.viewdepartmentlist;

import com.example.qlchamcong.HRSystem.DepartmentList;
import com.example.qlchamcong.HRSystem.IHRSystemAPIService;
import com.example.qlchamcong.changeGUIUtility.IActionChangeGUI;
import com.example.qlchamcong.changeGUIUtility.IPassArgument;

public class DepartmentListController {


    private final IActionChangeGUI navUtil;
    private final IPassArgument argumentUtil;

    private IHRSystemAPIService departmentList;
    private DepartmentList departList;
    public DepartmentListController(IActionChangeGUI navUtil, IPassArgument argumentUtil) {
        this.argumentUtil = argumentUtil;
        this.navUtil = navUtil;
        departList=new DepartmentList();
//        this.departmentList = ServiceInitializer.getDepartmentListService();
    }

//    public int getEmployeeId() {
//        return (int) argumentUtil.getSharedData("fromHomeToAttendanceRecord");
//    }

//    public List<Department> fetchListOfRecords() {
//        return departmentList.getListDepartment();
//    }

//    public void showUpdateModal() throws IOException {
//        navUtil.showModal("/com/example/qlchamcong/viewattendancerecord/modals/add-record.fxml", "Update attendance record");
//    }
}
