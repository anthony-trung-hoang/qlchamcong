package com.example.qlchamcong.HRSystem;


public class HRSystem  {
    private static IHRSystemAPIService departmentList;
 public  HRSystem(){
     departmentList= new DepartmentList() ;
 }
 public static IHRSystemAPIService getDeparmentList() {
        return departmentList;
    }

}
