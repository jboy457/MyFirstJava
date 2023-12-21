/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 3
* QUESTION: Question 1
*
* PURPOSE: Feeding your pet dog [7 Marks]
*/

import java.util.Scanner;

public class TaiwoAdejareA3Q1 {
    final static char LABRADOR = 'l';
    final static char GOLDEN_RETRIEVER = 'r';
    final static char GERMAN_SHEPHERD = 'g';
    final static char BERNESE_MOUNTAIN = 'b';
    final static char OTHER = 'o';

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        double dogWeight = readWeight(scnr);
        boolean isPuppy = readIsPuppy(scnr);
        double walkDistance = readActivityLevel(scnr);
        char dogBreed = readDogBreed(scnr);
        double foodAmount = findFoodAmount(dogWeight, isPuppy, walkDistance, dogBreed);
        
        printFoodAmount(foodAmount);
        scnr.close();
    }

    /*
     * "convertKGtoLB" method converts kilogram to pounds.
     * 
     * @paramters {double weightKG}
     * 
     * @returns double (weightInPounds)
     * 
     * @calulation {weightInKg * kilogramToPounds}
     */
    static double convertKGtoLB(double weightKG) {
        final double KG_TO_LB = 2.20462;
        return weightKG * KG_TO_LB;
    }

    /*
     * "readWeight" method read weight in user's preffered unit.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns double (dogWeight)
     */
    static double readWeight(Scanner scnr) {
        final String LB = "p";
        final String KG = "k";

        String preferredUnitName; // To Store selected full unit name.

        System.out.println(
                "Do you want to provide dog's weight in kilograms(kg) or pounds(lb)? Kindly Enter 'k' for kilograms or 'p' for pounds.");
        System.out.print("Weight Unit: ");

        // Get user preffered unit ('k' or 'p')
        String preferredUnit = scnr.next();

        // Check if user input is either 'k' or 'p' if not print error message and
        // re-assign prefered unit to pounds(p)
        if (preferredUnit.equals(KG)) {
            preferredUnitName = "kilogram (kg)";
        } else if (preferredUnit.equals(LB)) {
            preferredUnitName = "Pounds (lb)";
        } else {
            System.out.println(
                    "Sorry, we couldn't recognize the unit you entered. We'll assume you want to use pounds (lb) for your weight.");
            preferredUnit = LB;
            preferredUnitName = "pounds (lb)";
        }

        System.out.println("\nKindly enter your dog's weight in " + preferredUnitName);
        System.out.print("Dog's Weight: ");

        // Get dog weight
        double dogWeight = scnr.nextDouble();

        // Only convert the dog weight to lb if the unit selected is Kg.
        if (preferredUnit.equals(KG)) {
            dogWeight = convertKGtoLB(dogWeight);
        }

        return dogWeight;
    }

    /*
     * "readIsPuppy" method read if user dogs is a puppy or not.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns boolean (isPuppy)
     */
    static boolean readIsPuppy(Scanner scnr) {
        boolean isPuppy = false;

        final String YES = "y";
        final String NO = "n";

        System.out.println("\nIs the dog a puppy(a baby dog)? Kindly enter 'y' for yes or 'n' for no");
        System.out.print("Answer: ");

        // Get if dog is a puppy or not in string(yes(y) or no(n)).
        String isPuppyStr = scnr.next();

        // Only update isPuppy to true if yes is entered.
        if (isPuppyStr.equals(YES)) {
            isPuppy = !isPuppy;
        }

        // Check for wrong input and print error response message.
        if (!(isPuppyStr.equals(YES) || isPuppyStr.equals(NO))) {
            System.out.println(
                    "Sorry, we couldn't recognize the answer you entered. We'll assume that the dogs is not a puppy.");
        }

        return isPuppy;
    }

    /*
     * "convertKMtoMI" method converts kilometer to miles.
     * 
     * @paramters {double distanceKM}
     * 
     * @returns double (distanceMI)
     * 
     * @calulation {distanceInKilometer * kilometerToMiles}
     */
    static double convertKMtoMI(double distanceKM) {
        final double KM_TO_MI = 0.621371;
        return distanceKM * KM_TO_MI;
    }

    /*
     * "readActivityLevel" method should read in a dog’s daily walking distance.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns double (dogWalkingDistance)
     */
    static double readActivityLevel(Scanner scnr) {
        final String KM = "k";
        final String MI = "m";

        String preferredUnitName; // To Store selected full unit name.

        System.out.println(
                "\nDo you want to provide dog's distance in kilometer(km) or miles(mi)? Kindly Enter 'k' for kilometer or 'm' for miles.");
        System.out.print("Distance Unit: ");

        // Get user preffered unit ('k' or 'm')
        String preferredUnit = scnr.next();

        // Check if user input is either 'k' or 'm' if not print error message and
        // re-assign prefered unit to miles(m)
        if (preferredUnit.equals(KM)) {
            preferredUnitName = "kilometer (km)";
        } else if (preferredUnit.equals(MI)) {
            preferredUnitName = "miles (mi)";
        } else {
            System.out.println(
                    "Sorry, we couldn't recognize the unit you entered. We'll assume you want to use miles (mi) for your distance.");
            preferredUnit = MI;
            preferredUnitName = "miles (mi)";
        }

        System.out.println("\nKindly enter your dog's daily walking distance in " + preferredUnitName);
        System.out.print("Dog's Distance: ");

        // Get dog's walking distance
        double dogWalkDistance = scnr.nextDouble();

        // Only convert the dog walking distance to miles if the unit selected is
        // kilometer.
        if (preferredUnit.equals(KM)) {
            dogWalkDistance = convertKMtoMI(dogWalkDistance);
        }

        return dogWalkDistance;
    }

    /*
     * "readDogBreed" method should read in a dog’s breed.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns char (dogBreed)
     */
    static char readDogBreed(Scanner scnr) {
        char dogBreed;
        String errorMessage = "Sorry, we couldn't recognize the breed you entered. We'll assume that the dogs is other.";

        System.out.println("\nHere are the following types of dog bread: ");
        System.out.printf("'%s' for Labrador\n", LABRADOR);
        System.out.printf("'%s' for Golden retriever\n", GOLDEN_RETRIEVER);
        System.out.printf("'%s' for German Sherpherd\n", GERMAN_SHEPHERD);
        System.out.printf("'%s' for Bernese Mountain\n", BERNESE_MOUNTAIN);
        System.out.printf("'%s' for Other\n", OTHER);
        System.out.print("\nPlease enter a letter to represent your dog's breed: ");

        // Get the dog breed
        String dogBreedInput = scnr.next();

        // Check if string length is greater than one or less than one
        if (dogBreedInput.length() < 1 || dogBreedInput.length() > 1) {
            System.out.println(errorMessage);
            dogBreed = OTHER;
        }

        // Convert input to character.
        dogBreed = dogBreedInput.charAt(0);

        // Check for wrong input and print error response message.
        if (!(dogBreed == LABRADOR ||
                dogBreed == GOLDEN_RETRIEVER ||
                dogBreed == GERMAN_SHEPHERD ||
                      dogBreed == BERNESE_MOUNTAIN ||
                dogBreed == OTHER)) {
            System.out.println(errorMessage);
            dogBreed = OTHER;
        }

        return dogBreed;
    }

    /*
     * "findFoodAmount" method should compute the total amout of food quantity
     * 
     * @paramters {double dog}
     * 
     * @returns char (dogBreed)
     */
    static double findFoodAmount(double dogWeight, boolean isPuppy, double dogWalkDistance, char dogBreed) {
        double totalFoodAount = 0;

        final double QUATER_CUP = 0.25;
        final double HALF_CUP = 0.5;
        final double PER_4_MILES = 4;
        final double L_R_G_MAX_WEIGHT = 80;
        final double B_MAX_WEIGHT = 90;

        if(isPuppy) {
            // Puppies should eat a half cup of food per pound they weigh
            totalFoodAount += dogWeight * HALF_CUP;
        } else {
            // For every pound of the dog, they should have a quarter cup of food
            totalFoodAount += dogWeight * QUATER_CUP;
        }

        // For every four miles walked a day, a dog should get a quarter cup more food
        double cupPerQuaterMile = (dogWalkDistance / PER_4_MILES) * QUATER_CUP;
        totalFoodAount += cupPerQuaterMile; 

        // Labradors and Golden Retrievers should get a quarter cup more food per 5 pounds they weigh
        if(dogBreed == LABRADOR || dogBreed == GOLDEN_RETRIEVER) {
            final double PER_5_LB = 5;
            double cupPer5Lb = (dogWeight / PER_5_LB) * QUATER_CUP;
            totalFoodAount += cupPer5Lb;
        }
        
        // German Shepherds should get a quarter cup more food per seven pounds they weigh
        if(dogBreed == GERMAN_SHEPHERD) {
            final double PER_7_LB = 7;
            double cupPer7Lb = (dogWeight / PER_7_LB) * QUATER_CUP;
            totalFoodAount += cupPer7Lb;
        } 
       
        // Bernese Mountain Dogs dog should get a quarter cup less food per ten pounds they weigh
        if(dogBreed == BERNESE_MOUNTAIN) {
            final double PER_10_LB = 10;
            double cupPer10Lb = (dogWeight / PER_10_LB) * QUATER_CUP;
            totalFoodAount -= cupPer10Lb;
        }

        // Overweight dogs should get one cup less
        if(
            (dogBreed == LABRADOR || dogBreed == GOLDEN_RETRIEVER || dogBreed == GERMAN_SHEPHERD) && 
            (dogWeight > L_R_G_MAX_WEIGHT) && // For dogWeight > 90
            !isPuppy
        ) {
            totalFoodAount -= 1;
        }

        if(
            dogBreed == BERNESE_MOUNTAIN && 
            dogWeight > B_MAX_WEIGHT && // For dogWeight > 80
            !isPuppy
        ) {
            totalFoodAount -= 1;
        }

        // Puppies should also get one cup less if they arent overwieght.
        if(
            (dogBreed == LABRADOR || dogBreed == GOLDEN_RETRIEVER || dogBreed == GERMAN_SHEPHERD) && 
            (dogWeight < L_R_G_MAX_WEIGHT) && // For puppies < 90
            isPuppy
        ) {
            totalFoodAount -= 1;
        }

        if(
            dogBreed == BERNESE_MOUNTAIN && 
            dogWeight < B_MAX_WEIGHT && // For puppies < 80
            isPuppy
        ) {
            totalFoodAount -= 1;
        }

        if(dogBreed == OTHER && isPuppy) {
            totalFoodAount -= 1;
        }

        return totalFoodAount;
    }


    /*
     * "printFoodAmount" method should display the amount of cup to fead your dog.
     * 
     * @paramters {double foodAmount}
     * 
     * @returns void ()
     */
    static void printFoodAmount(double foodAmount) {
        System.out.printf("\n🐶 To keep your dog happy and healthy, we recommend feeding them around %.1f cups of food per day.", foodAmount);
    }
}