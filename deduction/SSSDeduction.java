package terminalassessment.deduction;

// SSS deduction implementation
public class SSSDeduction extends Deduction {
    // Constructor sets name to "SSS"
    public SSSDeduction() {
        super("SSS");  // Call parent constructor
    }

    // Calculate SSS contribution
    @Override
    public double calculate(double basicSalary) {
        if (basicSalary < 3250) return 135.0;         // Minimum bracket
        if (basicSalary >= 24750) return 1125.0;      // Maximum bracket
        double steps = Math.floor((basicSalary - 3250) / 500);  // Calculate steps
        return 135.0 + (steps + 1) * 22.50;          // Return calculated amount
    }
}