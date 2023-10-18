/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 2
* QUESTION: Question 1
*
* PURPOSE: Basic Calculator [10 points]
*/

import java.util.Scanner;

public class TaiwoAdejareA2Q1 {
    public static void main(String[] args) {
        Scanner keyIn = new Scanner(System.in);

        System.out.println("Enter the first number");
        double num1 = keyIn.nextDouble();

        System.out.println("Enter the second number");
        double num2 = keyIn.nextDouble();

        System.out.printf("Addition: %.2f\n", add(num1, num2));
        System.out.printf("Subtraction: %.2f\n", subtract(num1, num2));
        System.out.printf("Multiplication: %.2f\n", multiply(num1, num2));
        System.out.printf("Division: %.2f\n", divide(num1, num2));
        System.out.println("The program terminated normally.");

        keyIn.close();
    }

    /*
    * "add" method sum two numbers.
    * @paramters {double num1, double num2} 
    * @returns double (sum) 
    * @calulation {num1 + num2} 
    */
    static double add(double num1, double num2) {
        return num1 + num2;
    }

    /*
    * "subtract" method minus num2 from num1.
    * @paramters {double num1, double num2} 
    * @returns double (subtract) 
    * @calulation {num1 - num2} 
    */
    static double subtract(double num1, double num2) {
        return num1 - num2;
    }

    /*
    * "multiply" method multiply num2 and num1.
    * @paramters {double num1, double num2} 
    * @returns double (multiply) 
    * @calulation {num1 * num2} 
    */
    static double multiply(double num1, double num2) {
        return num1 * num2;
    }

    /*
    * "divide" method divide num1 by num1.
    * @paramters {double num1, double num2} 
    * @returns double (divide) 
    * @calulation {num1 / num2} 
    */
    static double divide(double num1, double num2) {
        return num1 / num2;
    }

    /*
    * "testOperations" method test the above operations.
    * @paramters - No Paramater! 
    * @returns void
    * @proccess - It test operation with two differnt test case and print out result. 
    */
    static void testOperations() {
        // Test Case 1
        double test1Num1 = 6;
        double test1Num2 = 3;
        System.out.println("------ Test Case 1 Started --------");
        System.out.printf("%.1f plus %.1f should equal 9.0, got %.1f\n", test1Num1, test1Num2, add(test1Num1, test1Num2));
        System.out.printf("%.1f minus %.1f should equal 3.0, got %.1f\n", test1Num1, test1Num2, subtract(test1Num1, test1Num2));
        System.out.printf("%.1f times %.1f should equal 18.0, got %.1f\n", test1Num1, test1Num2, multiply(test1Num1, test1Num2));
        System.out.printf("%.1f divided by %.1f should equals 2.0, got %.1f\n", test1Num1, test1Num2, divide(test1Num1, test1Num2));
        System.out.println("------ Test Case 1 Ended --------\n");

        // Test Case 2
        double test2Num1 = 0;
        double test2Num2 = 3;

        System.out.println("------ Test Case 2 Started --------");
        System.out.printf("%.1f plus %.1f should equal 3.0, got %.1f\n", test2Num1, test2Num2, add(test2Num1, test2Num2));
        System.out.printf("%.1f minus %.1f should equal -3.0, got %.1f\n", test2Num1, test2Num2, subtract(test2Num1, test2Num2));
        System.out.printf("%.1f times %.1f should equal 0.0, got %.1f\n", test2Num1, test2Num2, multiply(test2Num1, test2Num2));
        System.out.printf("%.1f divided by %.1f should equals 0, got %.1f\n", test2Num1, test2Num2, divide(test2Num1, test2Num2));
        System.out.println("------ Test Case 2 Ended --------");
    }


}
