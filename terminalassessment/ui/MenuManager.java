package terminalassessment.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import terminalassessment.model.Employee;
import terminalassessment.attendance.AttendanceRecord;
import terminalassessment.payroll.PayrollCalculator;
import java.util.*;

public class MenuManager {
    Scanner scanner;                      // Input handler
    Map<String, Employee> employees;      // Employee data
    AttendanceRecord attendance;          // Attendance data
    PayrollCalculator payrollCalculator;  // Calculator

    // Initialize with data
    public MenuManager(Map<String, Employee> employees, AttendanceRecord attendance) {
        scanner = new Scanner(System.in);  // Create scanner
        this.employees = employees;        // Store employees
        this.attendance = attendance;      // Store attendance
        payrollCalculator = new PayrollCalculator();  // Create calculator
    }

    // Display main menu
    public void showMenu() {
        int choice;
        do {
            // Print menu options
            System.out.println("\nWelcome to MotorPH Menu:");
            System.out.println("1. Display Employee Information");
            System.out.println("2. Compute Hours Worked");
            System.out.println("3. Compute Gross Salary");
            System.out.println("4. Compute Net Salary");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();  // Get user choice
            scanner.nextLine();          // Clear input buffer
            processChoice(choice);       // Handle selection
        } while (choice != 5);           // Loop until exit
    }

    // Route menu selection
    private void processChoice(int choice) {
        switch (choice) {
            case 1 -> displayEmployeeInfo();  // Show employee details
            case 2 -> computeHoursWorked();   // Calculate hours
            case 3 -> computeGrossSalary();   // Calculate gross pay
            case 4 -> computeNetSalary();     // Calculate net pay
            case 5 -> System.out.println("Exiting...");  // Exit message
            default -> System.out.println("Invalid choice.");  // Error
        }
    }

    // Option 1: Display employee information
    private void displayEmployeeInfo() {
        System.out.print("Enter Employee Number: ");
        String empNumber = scanner.nextLine().trim();  // Get input
        Employee emp = employees.get(empNumber);       // Find employee
        
        if (emp != null) {  // If found
            System.out.println("\nEmployee Details:");
            System.out.println("Employee Number: " + emp.getEmployeeNumber());
            System.out.println("Full Name: " + emp.getFullName());
            System.out.println("Birthday: " + emp.getBirthday());
            System.out.printf("Basic Salary: PHP %.2f%n", emp.getBasicSalary());
            System.out.printf("Hourly Rate: PHP %.2f%n", emp.getHourlyRate());
        } else {
            System.out.println("Employee not found.");  // Not found message
        }
    }

    // Option 2: Calculate hours worked
    private void computeHoursWorked() {
        System.out.print("Enter employee number: ");
        String empNumber = scanner.nextLine();  // Get ID
        
        // Get date range
        System.out.print("Enter start date (MM/dd/yyyy): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (MM/dd/yyyy): ");
        String endDateStr = scanner.nextLine();

        try {
            // Parse dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            
            // Get filtered records
            Map<String, String[]> attendanceData = 
                attendance.getAttendanceInRange(empNumber, startDate, endDate);
            
            long totalMinutes = 0;
            // Process each record
            for (Map.Entry<String, String[]> entry : attendanceData.entrySet()) {
                long minutes = calculateTimeDifferenceMinutes(
                    entry.getValue()[0], entry.getValue()[1]);  // Calculate minutes
                totalMinutes += minutes;  // Accumulate total
                // Print daily hours
                System.out.printf("Date: %s, Hours: %s%n", 
                    entry.getKey(), formatTimeDifference(minutes));
            }
            
            // Print total hours
            System.out.printf("Total Hours: %s%n", formatTimeDifference(totalMinutes));
        } catch (ParseException e) {
            System.out.println("Invalid date format.");  // Handle parse error
        }
    }

    // Option 3: Calculate gross salary
    private void computeGrossSalary() {
        System.out.print("Enter employee number: ");
        String empNumber = scanner.nextLine();  // Get ID
        Employee emp = employees.get(empNumber);  // Find employee
        
        if (emp == null) {  // Check existence
            System.out.println("Employee not found.");
            return;
        }

        // Get date range
        System.out.print("Enter start date (MM/dd/yyyy): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (MM/dd/yyyy): ");
        String endDateStr = scanner.nextLine();

        try {
            // Parse dates
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            
            // Get attendance records
            Map<String, String[]> attendanceData = 
                attendance.getAttendanceInRange(empNumber, startDate, endDate);
            
            long totalMinutes = 0;
            // Sum all minutes
            for (Map.Entry<String, String[]> entry : attendanceData.entrySet()) {
                totalMinutes += calculateTimeDifferenceMinutes(
                    entry.getValue()[0], entry.getValue()[1]);
            }
            
            // Calculate and display
            double totalHours = totalMinutes / 60.0;
            double grossSalary = totalHours * emp.getHourlyRate();
            System.out.printf("Gross salary for %s: PHP %.2f%n", emp.getFullName(), grossSalary);
        } catch (ParseException e) {
            System.out.println("Invalid date format.");  // Handle error
        }
    }

    // Option 4: Calculate net salary
    private void computeNetSalary() {
        System.out.print("Enter employee number: ");
        String empNumber = scanner.nextLine();  // Get ID
        Employee emp = employees.get(empNumber);  // Find employee
        
        if (emp == null) {  // Check existence
            System.out.println("Employee not found.");
            return;
        }

        // Calculate components
        double basicSalary = emp.getBasicSalary();
        double totalDeductions = payrollCalculator.calculateTotalDeductions(basicSalary);
        double taxableIncome = basicSalary - totalDeductions;
        double withholdingTax = PayrollCalculator.calculateWithholdingTax(taxableIncome);
        double netSalary = taxableIncome - withholdingTax;

        // Display breakdown
        System.out.println("\nNet Salary Calculation:");
        System.out.printf("Basic Salary: PHP %.2f%n", basicSalary);
        System.out.printf("Total Deductions: PHP %.2f%n", totalDeductions);
        System.out.printf("Taxable Income: PHP %.2f%n", taxableIncome);
        System.out.printf("Withholding Tax: PHP %.2f%n", withholdingTax);
        System.out.printf("Net Salary: PHP %.2f%n", netSalary);
    }

    // Calculate minutes between two times
    private long calculateTimeDifferenceMinutes(String logIn, String logOut) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("HH:mm");  // Time format
            Date timeIn = format.parse(logIn);  // Parse login time
            Date timeOut = format.parse(logOut); // Parse logout time
            return (timeOut.getTime() - timeIn.getTime()) / (60 * 1000);  // Difference in minutes
        } catch (ParseException e) {
            return -1;  // Error indicator
        }
    }

    // Format minutes to HH:mm
    private String formatTimeDifference(long minutes) {
        return (minutes < 0) ? "Invalid" :  // Handle errors
            String.format("%d:%02d", minutes / 60, minutes % 60);  // Format as hours:minutes
    }
}    

