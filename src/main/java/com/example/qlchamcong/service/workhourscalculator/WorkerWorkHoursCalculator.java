package com.example.qlchamcong.service.workhourscalculator;

import com.example.qlchamcong.entity.AttendanceRecord;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WorkerWorkHoursCalculator {
    public static List<Double> calculateWorkerWorkHours(List<AttendanceRecord> attendanceRecords) {
        double hoursShift1 = 0.0;
        double hoursShift2 = 0.0;
        double hoursShift3 = 0.0;

        if (attendanceRecords.size() >= 1) {

            for (int i = 0; i < attendanceRecords.size() - 1; i++) {
                AttendanceRecord record = attendanceRecords.get(i);
                Timestamp timestamp = record.getTimestamp();
                String type = record.getType();

                if ("checkin".equals(type)) {
                    AttendanceRecord checkoutRecord = attendanceRecords.get(i + 1);
                    if ("checkout".equals(checkoutRecord.getType())) {
                        Timestamp checkoutTime = checkoutRecord.getTimestamp();

                        System.out.println(isWithinShift(timestamp, 8, 12));

                        if (isWithinShift(timestamp, 8, 12) && isWithinShift(checkoutTime, 8, 12)) {
                            hoursShift1 += calculateWorkerWorkHours(timestamp, checkoutTime);
                        } else if (isWithinShift(timestamp, 13, 17) && isWithinShift(checkoutTime, 13, 17)) {
                            hoursShift2 += calculateWorkerWorkHours(timestamp, checkoutTime);
                        } else if (isWithinShift(timestamp, 18, 22) && isWithinShift(checkoutTime, 18, 22)) {
                            hoursShift3 += calculateWorkerWorkHours(timestamp, checkoutTime);
                        } else {
                            throw new UnknownShiftException("Cannot define shift for " + timestamp.toString() + " and " + checkoutTime.toString());
                        }
                    }

                }
            }
        }
        List<Double> result = new ArrayList<>();
        result.add(hoursShift1);
        result.add(hoursShift2);
        result.add(hoursShift3);


        return result;
    }

    public static double calculateWorkerWorkHours(Timestamp checkinTime, Timestamp checkoutTime) {
        long milliseconds = checkoutTime.getTime() - checkinTime.getTime();
        return milliseconds / (60.0 * 60.0 * 1000.0);
    }

    private static boolean isWithinShift(Timestamp timestamp, int startHour, int endHour) {
        int hour = timestamp.toLocalDateTime().getHour();
        return hour >= startHour && hour <= endHour;
    }

    public static class UnknownShiftException extends RuntimeException {
        public UnknownShiftException(String message) {
            super(message);
        }
    }
}
