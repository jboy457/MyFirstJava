/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 2
* QUESTION: Question 2
*
* PURPOSE: Mortgage Calculator [7 points]
*/

import java.util.Scanner;

public class TaiwoAdejareA2Q2 {
    final static int YEAR_TO_MONTH = 12;
    final static int PERCENT = 100;
    
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);

        System.out.print("Enter the loan amount: ");
        double loanAmount = keyIn.nextDouble();

        System.out.print("Enter the interest rate: ");
        double intrestRate = keyIn.nextDouble();

        System.out.print("Enter the loan period in years: ");
        double periodInYear = keyIn.nextDouble();


        printMortgageSummary(loanAmount, intrestRate, periodInYear);

        // Print method to test the "calculateMortgage" function.
        // testSuite();

        keyIn.close();
    }

    /*
    * "calculateMortgage" method calculate mortgate payment on month basis.
    * @paramters {double amount(loan amount), double rate(intrest rate), double period(loan duration)} 
    * @returns double (monthlyPaymentAmount) 
    * @process - Converted yearlyRate to decimal which converted to montly rate. I also converted the loan duration(periodInMonth) in years to month.
    * @calulation {(monthlyRates * loanAmont) / (1 - (1 + monthlyRates)^(-monthlyPayments))} 
    */
    static double calculateMortgage(double amount, double rate, double period) {
        double yearlyRate = rate / PERCENT;
        double monthlyRate = yearlyRate / YEAR_TO_MONTH;
        double periodInMonth = period * YEAR_TO_MONTH;

        double numerator  = monthlyRate * amount;
        double denominator = 1 - Math.pow((1 + monthlyRate), -periodInMonth);

        double monthlyPaymentAmount = numerator / denominator;

        return monthlyPaymentAmount;
    }

    /*
    * "printMortgageSummary" method prints the loan details..
    * @paramters {double amount(loan amount), double rate(intrest rate), double period(loan duration)} 
    * @returns void 
    * @process - Prints out the details of loan as expected
    */
    static void printMortgageSummary(double amount, double rate, double period) {
        System.out.printf("\n%14s is a summary of your loan based on the data provided:\n", "Here");
        System.out.printf("\nLoan Amount: %.2f", amount);
        System.out.printf("\nInterest Rate: %.2f%%", rate);
        System.out.printf("\nLoan Period: %.0f years\n", period);

        double monthlyPayment = calculateMortgage(amount, rate, period);
        double totalPayment = monthlyPayment * (period * YEAR_TO_MONTH); // monthlyPayment * totalMonths
        double totalInterest = totalPayment - amount;

        System.out.printf("\n%17s for your Loan Simulation:\n", "Results");
        System.out.printf("\nMonthly Payment: %.2f", monthlyPayment);
        System.out.printf("\nTotal Payment: %.2f", totalPayment);
        System.out.printf("\nTotal Interest Paid: %.2f\n", totalInterest);

        System.out.printf("\n%15s you for using the Mortgage Calculator!\n\n", "Thank");
        System.out.println("\nThe program terminated normally.");
    }

    /*
    * "testSuite" method test the calculate mortgage function....
    * @paramters - No parameter!
    * @returns void
    * @process - Created two test cases and tested the calculateMortgage method.
    */
    static void testSuite() {
        // Test Case 1
        System.out.println("------ Test Case 1 Started --------");
        double amount1 = 5000;
        double rate1 = 2.3;
        double period1 = 4;

        double monthlyPayment1 = calculateMortgage(amount1, rate1, period1);
        System.out.printf("Monthly payment should be 109.13, but got %.2f\n", monthlyPayment1);
        System.out.println("------ Test Case 1 Ended --------\n");

        // Test Case 2
        System.out.println("------ Test Case 2 Started --------");
        double amount2 = 1500;
        double rate2 = 10;
        double period2 = 1;

        double monthlyPayment2 = calculateMortgage(amount2, rate2, period2);
        System.out.printf("Monthly payment should be 131.87, but got %.2f\n", monthlyPayment2);
        System.out.println("------ Test Case 2 Ended --------");

        System.out.println("Test Completed");
    }
}
