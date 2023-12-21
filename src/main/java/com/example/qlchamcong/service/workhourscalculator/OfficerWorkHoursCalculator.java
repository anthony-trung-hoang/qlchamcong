package com.example.qlchamcong.service.workhourscalculator;

import com.example.qlchamcong.entity.AttendanceRecord;
import com.example.qlchamcong.exception.UnknownShiftException;

import java.sql.Timestamp;
import java.util.List;

public class OfficerWorkHoursCalculator {

    public static Double[] calculateOfficeWorkHours(List<AttendanceRecord> attendanceRecords) {
        Double[] result = new Double[4];
        result[0] = 0.0; // Ca sáng
        result[1] = 0.0; // Ca chiều
        result[2] = 0.0; // Tổng thời gian đi muộn
        result[3] = 0.0; // Tổng thời gian về sớm

        boolean isInMorningShift = false;
        boolean isInAfternoonShift = false;

        double totalLateTime = 0.0;
        double totalEarlyLeaveTime = 0.0;

        if (attendanceRecords.size() >= 1) {


            for (int i = 0; i < attendanceRecords.size() - 1; i++) {
                AttendanceRecord record = attendanceRecords.get(i);
                Timestamp timestamp = record.getTimestamp();
                String type = record.getType();

                if ("checkin".equals(type)) {
                    AttendanceRecord checkoutRecord = attendanceRecords.get(i + 1);
                    if ("checkout".equals(checkoutRecord.getType())) {
                        Timestamp checkoutTime = checkoutRecord.getTimestamp();

                        if (isWithinShift(timestamp, 8, 30, 12, 30) && isWithinShift(checkoutTime, 8, 30, 12, 30)) {
                            result[0] += calculateWorkHours(timestamp, checkoutTime);
                            isInMorningShift = true;
                        } else if (isWithinShift(timestamp, 13, 0, 17, 30) && isWithinShift(checkoutTime, 13, 0, 17, 30)) {
                            result[1] += calculateWorkHours(timestamp, checkoutTime);
                            isInAfternoonShift = true;
                        } else {
                            throw new UnknownShiftException("Cannot define shift for " + timestamp + " and " + checkoutTime.toString());
                        }

                        if (isInMorningShift) {
                            double lateTime = calculateLateTime(timestamp, 8, 30);
                            totalLateTime += lateTime;
                            double earlyLeave = calculateEarlyLeaveTime(checkoutTime, 12, 0);
                            totalEarlyLeaveTime += earlyLeave;
                        } else {
                            double lateTime = calculateLateTime(timestamp, 13, 0);
                            totalLateTime += lateTime;
                            double earlyLeave = calculateEarlyLeaveTime(checkoutTime, 17, 30);
                            totalEarlyLeaveTime += earlyLeave;
                        }

                        isInMorningShift = false;
                        isInAfternoonShift = false;
                    }
                }
            }

            result[0] = (result[0] > 0) ? 1.0 : 0.0;
            result[1] = (result[1] > 0) ? 1.0 : 0.0;

            result[2] = totalLateTime;
            result[3] = totalEarlyLeaveTime;

        }
        return result;
    }
    public static double calculateWorkHours(Timestamp checkinTime, Timestamp checkoutTime) {
        long milliseconds = checkoutTime.getTime() - checkinTime.getTime();
        return milliseconds / (60.0 * 60.0 * 1000.0);
    }

    private static boolean isWithinShift(Timestamp timestamp, int startHour, int startMinute, int endHour, int endMinute) {
        int hour = timestamp.toLocalDateTime().getHour();
        int minute = timestamp.toLocalDateTime().getMinute();
//        System.out.println("hour " + hour);
//        System.out.println("minute " + minute);
        if (hour > startHour && hour < endHour) {
            return true;
        } else if (hour == startHour && minute >= startMinute) {
            return true;
        } else return hour == endHour && minute <= endMinute;
    }


    private static double calculateLateTime(Timestamp timestamp, int startHour, int startMinute) {
//        start = 8, startMin = 30
        int hour = timestamp.toLocalDateTime().getHour(); //8
        int minute = timestamp.toLocalDateTime().getMinute(); //40

        if (hour == startHour && minute >= startMinute) {
            return  (minute - startMinute) / 60.0;
        } else if (hour > startHour) {
            return ((hour - startHour) * 60 + minute - startMinute) / 60.0;
        } else {
            return 0.0;
        }
    }

    private static double calculateEarlyLeaveTime(Timestamp checkoutTime, int endHour, int endMinute) {
        int hour = checkoutTime.toLocalDateTime().getHour();
//        System.out.println("Checkout hour: " + hour);
        int minute = checkoutTime.toLocalDateTime().getMinute();
//        System.out.println("Checkout minute: " + minute);


        if (hour == endHour && minute < endMinute) {
            return (endMinute - minute) / 60.0;
        } else if (hour < endHour) {
            return ((endHour - hour) * 60 + endMinute - minute) / 60.0;
        } else {
            return 0.0;
        }
    }
}