package terminalassessment.attendance;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Manages attendance records
public class AttendanceRecord {
    Map<String, Map<String, String[]>> attendanceData;  // Employee -> Date -> Times
    
    // Initialize data structure
    public AttendanceRecord() {
        attendanceData = new HashMap<>();  // Create empty map
    }

    // Add attendance record
    public void addAttendance(String empNumber, String date, String logIn, String logOut) {
        attendanceData.putIfAbsent(empNumber, new HashMap<>());  // Add employee if new
        attendanceData.get(empNumber).put(date, new String[]{logIn, logOut});  // Add times
    }

    // Get records within date range
    public Map<String, String[]> getAttendanceInRange(String empNumber, Date startDate, Date endDate) {
        Map<String, String[]> filteredRecords = new HashMap<>();  // Result storage
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  // Date parser
        
        // Process each record
        attendanceData.getOrDefault(empNumber, new HashMap<>()).forEach((date, times) -> {
            try {
                Date currentDate = dateFormat.parse(date);  // Parse date
                // Check if within range
                if (!currentDate.before(startDate) && !currentDate.after(endDate)) {
                    filteredRecords.put(date, times);  // Add to results
                }
            } catch (ParseException e) {
                System.err.println("Error parsing date: " + date);  // Handle error
            }
        });
        return filteredRecords;  // Return filtered records
    }
}

