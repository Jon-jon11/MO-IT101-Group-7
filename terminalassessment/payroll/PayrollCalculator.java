package terminalassessment.payroll;
import java.util.ArrayList;
import java.util.List;
import terminalassessment.deduction.Deduction;
import terminalassessment.deduction.PagIBIGDeduction;
import terminalassessment.deduction.PhilHealthDeduction;
import terminalassessment.deduction.SSSDeduction;


// Handles payroll calculations
public class PayrollCalculator {
    List<Deduction> deductions;  // List of deductions
    
    // Initialize with standard deductions
    public PayrollCalculator() {
        deductions = new ArrayList<>();  // Create list
        deductions.add(new SSSDeduction());  // Add SSS
        deductions.add(new PhilHealthDeduction());  // Add PhilHealth
        deductions.add(new PagIBIGDeduction());  // Add Pag-IBIG
    }

    // Calculate total deductions
    public double calculateTotalDeductions(double basicSalary) {
        return deductions.stream()  // Create stream
            .mapToDouble(d -> d.calculate(basicSalary))  // Calculate each
            .sum();  // Sum all deductions
    }

    // Calculate withholding tax
    public static double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20832) return 0.0;                          // Tax exempt
        if (taxableIncome <= 33333) return (taxableIncome - 20833) * 0.20; // 20% bracket
        if (taxableIncome <= 66667) return 2500 + (taxableIncome - 33333) * 0.25; // 25%
        if (taxableIncome <= 166667) return 10833 + (taxableIncome - 66667) * 0.30; // 30%
        if (taxableIncome <= 666667) return 40833.33 + (taxableIncome - 166667) * 0.32; // 32%
        return 200833.33 + (taxableIncome - 666667) * 0.35;               // 35% bracket
    }
}

