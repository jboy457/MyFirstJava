/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 5
* QUESTION: Question 2
*
* PURPOSE: Prime numbers for message decryption [4 marks]
*/

import java.util.Scanner;

public class TaiwoAdejareA5Q2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Get number of character to decrypt
        System.out.print("Enter the number of characters of the encrypted message: ");
        String numOfChar = scnr.nextLine();

        // Check if valid number was entered.
        if (!isStrANumber(numOfChar)) {
            System.out.println("Invalid Input");
        } else {
            // Get array prime char and split for sorting.
            System.out.print("Enter the first " + numOfChar + " prime numbers in a random order: ");
            String key = scnr.nextLine();
            String[] keyArr = key.split(" ");

            // Get encrypted char and split for decryption.
            System.out.print("Enter the encrypted sentence with " + numOfChar + " characters: ");
            String encrypted = scnr.nextLine();
            String[] encryptedArr = encrypted.split("");

            // Check if prime number(key) and encrypted str have the same size.
            if (encryptedArr.length != keyArr.length) {
                System.out.println("Invalid Input");
            } else {
                String decrypted = "";
                // Using bubble sorting - Sort prime number and also sort encrypted str at the
                // same time.
                for (int i = 0; i < keyArr.length; i++) {
                    for (int j = i + 1; j < keyArr.length; j++) {
                        String temp = "";
                        String tempChar = "";

                        // Convert char in arr to int
                        int tempI = Integer.parseInt(keyArr[i]);
                        int tempJ = Integer.parseInt(keyArr[j]);

                        if (tempI > tempJ) {
                            temp = keyArr[i];
                            tempChar = encryptedArr[i];

                            // Sort prime number(key)
                            keyArr[i] = keyArr[j];
                            encryptedArr[i] = encryptedArr[j];

                            // Sort encrypted with same index.
                            keyArr[j] = temp;
                            encryptedArr[j] = tempChar;
                        }
                    }
                }

                // Convert sorted encrypted array into decrypted String.
                for (String character : encryptedArr) {
                    decrypted += character;
                }

                System.out.println("The decrypted sentence is: " + decrypted);
            }

        }

        scnr.close();
    }

    /*
     * "isStrANumber" method check if string can be parsed into an integer. i.e all
     * values within the string is a number.
     * 
     * @paramters {String str}
     * 
     * @returns boolean (isNumber)
     */
    public static boolean isStrANumber(String str) {
        boolean isNumber = false;
        boolean errFound = false;
        // Check if input is valid digit
        for (int i = 0; i < str.length(); i++) {
            if (!errFound) {
                char tempChar = str.charAt(i);
                if (Character.isDigit(tempChar)) {
                    isNumber = true;
                } else {
                    isNumber = false;
                    errFound = true;
                }
            }

        }

        return isNumber;
    }
}
