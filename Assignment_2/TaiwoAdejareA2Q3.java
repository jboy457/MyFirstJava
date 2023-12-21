/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 2
* QUESTION: Question 3
*
* PURPOSE: Investment and the Impact of Inflation [7 points]
*/

import java.util.Scanner;

public class TaiwoAdejareA2Q3 {
    final static int PERCENT = 100;
    // Global years due to "adjustInfaltion" method not accepting years a paramter.
    static double years; 
    
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);
    
        System.out.print("Enter the initial investment amount: ");
        double amount = keyIn.nextDouble();

        System.out.print("Enter the annual interest rate: ");
        double rate = keyIn.nextDouble();
        
        System.out.print("Enter the number of years for the investment: ");
        years = keyIn.nextDouble();

        System.out.print("Enter the inflation rate: ");
        double inflationRate = keyIn.nextDouble();

        // Print out summary of investment. 
        summaryOfInvestment(amount, rate, years, inflationRate);

        // Uncomment below to run test
        // testSuite();

        keyIn.close();
    }

   /*
    * "calculateCompoundInterest" method calculate compound interest.
    * @paramters {double amount(investment amount), double rate(intrest rate), double years(number of years)} 
    * @returns double (compountInterest) 
    * @calulation {investedAmount * (1 + (investmentRate / 100)^investmentYears)} 
    */
    static double calculateCompoundInterest(double amount, double rate, double years)  {
        double compountInterest = amount * Math.pow((1 + (rate / PERCENT)), years);
        return compountInterest;
    }

    /*
    * "adjustForInflation" method calculate adjusted inflation rate.
    * @paramters {double compountInterest, double inflationRate(inflation rate)} 
    * @returns double (adjustment) 
    * @calulation {compountInterest * (1 - (inflationRate / 100)^investmentYears)} 
    */
    static double adjustForInflation(double compountInterest, double inflationRate) {
        double adjustment = compountInterest * Math.pow((1 - (inflationRate / PERCENT)), years);
        return adjustment;
    }

    /*
    * "summaryOfInvestment" method prints investment summary deatils..
    * @paramters {double amount(investment amount), double rate(intrest rate), double years(number of years)} 
    * @returns void 
    * @process - Prints out the details of investment as expected
    */
    static void summaryOfInvestment(double amount, double rate, double years, double inflationRate) {
        double compountInterest = calculateCompoundInterest(amount, rate, years);
        double interestGain = compountInterest - amount;
        System.out.printf("\n%23s of your investment based on the data provided: \n", "Summary");
        
        System.out.printf("\nInitial investment amount: %.2f", amount);
        System.out.printf("\nAnnual interest rate: %.2f%%", rate);
        System.out.printf("\nNumber of years for the investment: %.0f", years);
        System.out.printf("\nTotal gain from interest: %.2f", interestGain);
        System.out.printf("\nTotal amount after investment: %.2f\n", compountInterest);

        double adjustedInterest = adjustForInflation(compountInterest, inflationRate);
        double gainFromInterest =  adjustedInterest - amount;
        System.out.printf("\n%22s of inflation on your investment: \n", "Impact");
        
        System.out.printf("\nTotal gain from interest (adjusted for inflation): %.2f", gainFromInterest);
        System.out.printf("\nTotal amount after investment (adjusted for inflation): %.2f\n", adjustedInterest);

        System.out.println("\nThe program terminated normally.");
    }

      /*
    * "testSuite" method test the calculate function....
    * @paramters - No parameter!
    * @returns void
    * @process - Created two test cases and tested the "calculateCompoundInterest" and "adjustForInflation" method.
    */
    static void testSuite() {
        // Test Case 1
        System.out.println("------ Test Case 1 Started --------");
        double amount1 = 5000;
        double rate1 = 0.0;
        double year1 = 4;
        double inflationRate1 = 2;

        double compountInterest1 = calculateCompoundInterest(amount1, rate1, year1);
        double adjustedInterest1 = adjustForInflation(compountInterest1, inflationRate1);

        System.out.printf("Compound Interest should be 5000.00, but got %.2f\n", compountInterest1);
        System.out.printf("Adjusted Interest should be 5000.00, but got %.2f\n", adjustedInterest1);

        System.out.println("------ Test Case 1 Ended --------\n");

        // Test Case 2
        System.out.println("------ Test Case 2 Started --------");
        double amount2 = 0.0;
        double rate2 = 2.3;
        double year2 = 4;
        double inflationRate2 = 2;

        double compountInterest2 = calculateCompoundInterest(amount2, rate2, year2);
        double adjustedInterest2 = adjustForInflation(compountInterest2, inflationRate2);

        System.out.printf("Compound Interest should be 0.00, but got %.2f\n", compountInterest2);
        System.out.printf("Adjusted Interest should be 0.00, but got %.2f\n", adjustedInterest2);

        System.out.println("------ Test Case 2 Ended --------\n");

       
        System.out.println("Test Completed");
    }
}
