package terminalassessment.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import terminalassessment.attendance.AttendanceRecord;
import terminalassessment.model.Employee;

// Handles data loading from files
public class DataLoader {
    // Load employee data from CSV
    public static Map<String, Employee> loadEmployees(String filePath) throws IOException {
        Map<String, Employee> employees = new HashMap<>();  // Create storage
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();  // Skip header
            String line;
            while ((line = br.readLine()) != null) {  // Read each line
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);  // Split CSV
                if (row.length >= 19) {  // Validate columns
                    String empNumber = row[0].trim();  // Get ID
                    String fullName = row[2].trim() + " " + row[1].trim();  // Build name
                    String basicSalaryStr = row[13].trim().replaceAll("[,\"]", "");  // Clean salary
                    String hourlyRateStr = row[18].trim().replaceAll("[,\"]", "");  // Clean rate
                    
                    // Create and store employee
                    employees.put(empNumber, new Employee(
                        empNumber,
                        fullName,
                        row[3].trim(),
                        Double.parseDouble(basicSalaryStr),
                        Double.parseDouble(hourlyRateStr)
                    ));
                }
            }
        }
        return employees;  // Return populated map
    }

    // Load attendance data from CSV
    public static void loadAttendance(AttendanceRecord attendance, String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();  // Skip header
            String line;
            while ((line = br.readLine()) != null) {  // Read each line
                String[] data = line.split(",");  // Split CSV
                if (data.length == 6) {  // Validate columns
                    String empNumber = data[0].trim();  // Get ID
                    String date = data[3].trim();      // Get date
                    String logIn = data[4].trim();     // Get login time
                    String logOut = data[5].trim();    // Get logout time
                    attendance.addAttendance(empNumber, date, logIn, logOut);  // Add record
                }
            }
        }
    }
}