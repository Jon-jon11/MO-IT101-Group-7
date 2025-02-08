package motorphbasicstructure;
import java.util.Scanner;

/**
 *
 * @author it101_group-7
 */

public class Motorphbasicstructure {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //login page
        System.out.println("-----------------\n| MotorPH Login |\n-----------------");
        System.out.print("Username: ");
        String usr_id = scan.nextLine();
        System.out.print("Password: ");
        String usr_pw = scan.nextLine();

        //welcome Message to our user
        System.out.println("\n\n.\n..\n...\n\nHello, " + usr_id + "!");
        
        //landing page
        System.out.println("\nWelcome to MotorPH Employee Portal");
        System.out.print("Enter Employee ID: ");
        String emp_id = scan.nextLine();

        //draft employee page
        System.out.println("\n\nShowing details for employee: " + emp_id);
        System.out.println("Full Name: Garcia, Manuel III");
        System.out.println("Date of Birth: 10/11/1983");

        System.out.println("\n\n--Salary Computation--");
        System.out.print("Enter total number of hours worked for the week: ");
        String emp_hours = scan.nextLine();

        //draft working hours page
        System.out.println("\n\n--Salary Computation--");
        System.out.println("Showing salary details for employee " + emp_id + ", with " + emp_hours + " hours worked this week.");
        System.out.println("Gross Weekly Salary: P21,428.40");
        System.out.println("Net Weekly Salary: P14,854.54");
    }

    //blank the screen function para maganda tignan later on -- there are better ways to do this as well this is just incase we're running it purely sa ide console
    public static void clearScreen() {
        for (int i = 0; i < 300; i++) {
            System.out.println();
        }
    }
}
