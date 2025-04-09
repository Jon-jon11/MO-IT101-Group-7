package terminalassessment.deduction;

 // Base class for all deduction types
public abstract class Deduction {
    String name;  // Name of the deduction
    
    // Constructor to initialize deduction name
    public Deduction(String name) {
        this.name = name;  // Set deduction name
    }
    
    // Abstract method to calculate deduction amount
    public abstract double calculate(double amount);
    
    // Getter for deduction name
    public String getName() {
        return name;  // Return name of deduction
    }
}
