package terminalassessment.model;

// Represents an employee
public class Employee {
    String employeeNumber;   // Employee ID
    String fullName;        // Full name
    String birthday;        // Date of birth
    double basicSalary;     // Monthly salary
    double hourlyRate;      // Hourly wage

    // Constructor to initialize employee
    public Employee(String employeeNumber, String fullName, String birthday, 
                   double basicSalary, double hourlyRate) {
        this.employeeNumber = employeeNumber;  // Set ID
        this.fullName = fullName;              // Set name
        this.birthday = birthday;             // Set birthday
        this.basicSalary = basicSalary;       // Set salary
        this.hourlyRate = hourlyRate;         // Set hourly rate
    }

    // Getter methods
    public String getEmployeeNumber() { return employeeNumber; }
    public String getFullName() { return fullName; }
    public String getBirthday() { return birthday; }
    public double getBasicSalary() { return basicSalary; }
    public double getHourlyRate() { return hourlyRate; }
}