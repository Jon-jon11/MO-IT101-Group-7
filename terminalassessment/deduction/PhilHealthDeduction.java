package terminalassessment.deduction;


// PhilHealth deduction implementation
public class PhilHealthDeduction extends Deduction {
    // Constructor sets name to "PhilHealth"
    public PhilHealthDeduction() {
        super("PhilHealth");  // Call parent constructor
    }

    // Calculate PhilHealth contribution
    @Override
    public double calculate(double basicSalary) {
        if (basicSalary <= 10000) return 150.0;       // Fixed rate
        if (basicSalary < 60000) return basicSalary * 0.015;  // 1.5% calculation
        return 900.0;                                 // Maximum contribution
    }
}    

