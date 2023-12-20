package com.example.qlchamcong.entity;

import java.util.List;

public class Tuple2<K, V> {
    private List<K> workerAttendanceDataList;
    private List<V> officerAttendanceDataList;

    public Tuple2(List<K> workerAttendanceDataList, List<V> officerAttendanceDataList) {
        this.workerAttendanceDataList = workerAttendanceDataList;
        this.officerAttendanceDataList = officerAttendanceDataList;
    }

    public List<K> getWorkerAttendanceDataList() {
        return workerAttendanceDataList;
    }

    public void setWorkerAttendanceDataList(List<K> workerAttendanceDataList) {
        this.workerAttendanceDataList = workerAttendanceDataList;
    }

    public List<V> getOfficerAttendanceDataList() {
        return officerAttendanceDataList;
    }

    public void setOfficerAttendanceDataList(List<V> officerAttendanceDataList) {
        this.officerAttendanceDataList = officerAttendanceDataList;
    }
}
