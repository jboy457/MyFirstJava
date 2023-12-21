/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 3
* QUESTION: Question 2
*
* PURPOSE: Farkle Scoring [8 Marks]
*/

import java.util.Scanner;

public class TaiwoAdejareA3Q2 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        // Get user dice input.
        String dice = readDice(scnr);

        // Score user input based on the given table
        int score = scoreDice(dice);

        // Print result based on thier score.
        printScore(score);

        scnr.close();
    }

    /*
     * "readDice" method should read in the current dice from the for scoring
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns String (diceInput)
     */
    static String readDice(Scanner scnr) {
        System.out.print("Dice to Score: ");
        String diceInput = scnr.nextLine();

        return diceInput;
    }

    /*
     * "scoreDice" method should determine the score based on the scoring rules given
     * above
     * 
     * @paramters {String dice}
     * 
     * @returns int (diceScore)
     */
    static int scoreDice(String dice) {
        // Dice Roll Points
        final int P_ONE = 100;
        final int P_FIVE = 50;
        final int P_3ONE = 1000;
        final int P_3TWO = 200;
        final int P_3THREE = 300;
        final int P_3FOUR = 400;
        final int P_3FIVE = 500;
        final int P_3SIX = 600;
        final int P_1TO6 = 3000;
        final int P_3PAIR = 1500;

        // Dice Rolls.
        final String ONE = "1";
        final String FIVE = "5";
        final String THREE_ONE = "111";
        final String THREE_TWO = "222";
        final String THREE_THREE = "333";
        final String THREE_FOUR = "444";
        final String THREE_FIVE = "555";
        final String THREE_SIX = "666";
        final String ONE_TO_SIX = "123456";

        int diceScore = 0;
        String diceToUpdate = dice; // Accepting paramter value to be worked on/updated --- i mean to be dropped after scoring..

        // You have to have length of 6 dice number for you to have 3 pair outcome and since we having a pair the is should divisible by 11(first possible pair)
        final int FIRST_POSS_PAIR = 11;
        if (diceToUpdate.length() == 6 &&
                Integer.parseInt(diceToUpdate.substring(0, 2)) % FIRST_POSS_PAIR == 0 &&
                Integer.parseInt(diceToUpdate.substring(2, 4)) % FIRST_POSS_PAIR == 0 &&
                Integer.parseInt(diceToUpdate.substring(4, 6)) % FIRST_POSS_PAIR == 0) {
            diceScore += P_3PAIR;
            diceToUpdate = diceToUpdate.replace(dice, "");
        }

        /* For each check below: 
         * Step 1: Check if roll exist in dice roll
         * Step 2: Add score if roll exist as given on the
         * Step 3: Drop dice that after score is added.
         */

        final int NOT_FOUND = -1;

        // Check if the diceInput matches 1 - 6 values
        if (diceToUpdate.equals(ONE_TO_SIX)) {
            diceScore += P_1TO6;
            diceToUpdate = diceToUpdate.replace(ONE_TO_SIX, ""); 
        }

        // Check if the diceInput matches 3 ones.
        if (diceToUpdate.indexOf(THREE_ONE) != NOT_FOUND) {
            diceScore += P_3ONE;
            diceToUpdate = diceToUpdate.replace(THREE_ONE, "");
        }
        
        // Check if the diceInput matches 3 six;
        if (diceToUpdate.indexOf(THREE_SIX) != NOT_FOUND) {
            diceScore += P_3SIX;
            diceToUpdate = diceToUpdate.replace(THREE_SIX, "");
        }

        // Check if the diceInput matches 3 fives;
        if (diceToUpdate.indexOf(THREE_FIVE) != NOT_FOUND) {
            diceScore += P_3FIVE;
            diceToUpdate = diceToUpdate.replace(THREE_FIVE, "");
        }

        // Check if the diceInput matches 3 fours;
        if (diceToUpdate.indexOf(THREE_FOUR) != NOT_FOUND) {
            diceScore += P_3FOUR;
            diceToUpdate = diceToUpdate.replace(THREE_FOUR, "");
        }

        // Check if the diceInput matches 3 threes;
        if (diceToUpdate.indexOf(THREE_THREE) != NOT_FOUND) {
            diceScore += P_3THREE;
            diceToUpdate = diceToUpdate.replace(THREE_THREE, "");
        }

        // Check if the diceInput matches 3 twos;
        if (diceToUpdate.indexOf(THREE_TWO) != NOT_FOUND) {
            diceScore += P_3TWO;
            diceToUpdate = diceToUpdate.replace(THREE_TWO, "");
        }

        // Check if the diceInput matches One
        if (diceToUpdate.indexOf(ONE) != NOT_FOUND) {
            // Check if theres mutiple ones other than 111 e.g 11
            int digitCount = diceToUpdate.length() - diceToUpdate.replace(ONE, "").length();

            // if mutiple count then multiply the score by the digit counts.
            if (digitCount == 0) {
                diceScore += diceToUpdate.length() * P_ONE;
            } else {
                diceScore += digitCount * P_ONE;
            }
            diceToUpdate = diceToUpdate.replace(ONE, "");
        }

        // Check if the diceInput matches One
        if (diceToUpdate.indexOf(FIVE) != NOT_FOUND) {
            // Check if theres mutiple fives other than 555 e.g 55
            int digitCount = diceToUpdate.length() - diceToUpdate.replace("1", "").length();

            // if mutiple count then multiply the score by the digit counts.
            if (digitCount == 0) {
                diceScore += diceToUpdate.length() * P_FIVE;
            } else {
                diceScore += digitCount * P_FIVE;
            }
            diceToUpdate = diceToUpdate.replace(FIVE, "");
        }

        return diceScore;
    }

    /*
     * "printScore" method print a nice message telling the user how much they scored.
     * 
     * @paramters {int score}
     * 
     * @returns void ()
     */
    static void printScore(int score) {
        // Check if score is 0 or less.
        if (score < 1) {
            System.out.println("Farkle!");
        } else {
            System.out.println("Points Earned: " + score);
        }
    }
}
