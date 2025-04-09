package terminalassessment;  // Must match folder name!

// Import necessary packages (adjust based on your code)
import terminalassessment.data.DataLoader;
import terminalassessment.attendance.AttendanceRecord;
import terminalassessment.ui.MenuManager;
import java.io.IOException;
import java.util.Map;
import terminalassessment.model.Employee;

public class Terminalassessmentmain {  // Class name matches filename
    public static void main(String[] args) {
        try {
            // Load data (update file paths if needed)
            Map<String, Employee> employees = DataLoader.loadEmployees("src/motorph_employee_data_complete.csv");
            AttendanceRecord attendance = new AttendanceRecord();
            DataLoader.loadAttendance(attendance, "src/attendance_record.csv");
            
            // Start application
            new MenuManager(employees, attendance).showMenu();
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}