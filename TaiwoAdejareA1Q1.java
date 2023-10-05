/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 1
* QUESTION: Question 1
*
* PURPOSE: Powered by Apples [12 points]
*/
public class TaiwoAdejareA1Q1 {
    public static void main(String[] args) {
        /*
         * Question 1a (Calculate Intake)
         * Purpose: Calculate the total calories taken
         * URL: https://www.webmd.com/diet/healthtool-food-calorie-counter
         */
        // Selected food item name from the url above
        final String FOOD_ITEM = "Bananas";
        final int CALOREIS_PER_ITEM = 121; 

        int numOfFoodItem = 20;

        int totalCalories = CALOREIS_PER_ITEM * numOfFoodItem; // Calculate total calories taken.
        System.out.println(numOfFoodItem + " " + FOOD_ITEM + " contain a total of " + totalCalories + " calories.");


        /*
         * Question 1b (Burn it off)
         * Purpose: Calculate how to burn the calories off.
         */
        final double KG_TO_LBS = 2.20462;
        final int HOUR_TO_MIN = 60;

        // Weight range in pounds(lbs)
        final int WEIGHT_1 = 130; // x1
        final int WEIGHT_2 = 155; // x2

        // Calories burned per hours(calories/hr)
        final int CALOREIS_PER_HOUR_1 = 207; // y1
        final int CALOREIS_PER_HOUR_2 = 246; // y2

        // Burn rate in calories/hr
        final double BURN_RATE = (CALOREIS_PER_HOUR_2 - CALOREIS_PER_HOUR_1) / (WEIGHT_2 - WEIGHT_1); // Slope = m = (y2 - y1) / (x2 - x1)
        final double Y_INTERCEPT = CALOREIS_PER_HOUR_2 - (BURN_RATE * WEIGHT_2); // yIntercept = b = y2 - (m * x2)

        // Convert user weight to pounds(lbs)
        double userWeightInKg = 75;
        double userWeightInLbs = userWeightInKg * KG_TO_LBS;
        System.out.printf("You are " + userWeightInKg + " Kilograms or %.2f pounds.\n", userWeightInLbs);

        // Calculate user's burn rate
        double userBurnRate = (BURN_RATE * userWeightInLbs) + Y_INTERCEPT; // user burn rate in calories/hr = y = mx + b
        double userBurnRateInMin = userBurnRate / HOUR_TO_MIN; 
        System.out.printf("When walking, you consume %.2f calories per minute.\n", userBurnRateInMin);

        // Calculate user's walking time to burn calories in Q1a
        double walkTimeInMin = totalCalories / userBurnRateInMin;
        System.out.printf("You would be able to walk %.2f minutes if you ate " + numOfFoodItem + " " + FOOD_ITEM + ".\n", walkTimeInMin);


        /*
         * Question 1c (How Far Can You Walk)
         * Purpose: Calculate how far can the user walk.
         */
        final double MILE_TO_KM = 1.60934;

        // Convert walking time in 1b which is in minutes to hour
        double walkingSpeed = 3.0; 
        double walkTimeInHr = walkTimeInMin / HOUR_TO_MIN; 

        // Calculate walking distance in miles and convert it to kilometer
        double walkDistance = walkingSpeed * walkTimeInHr; 
        double walkDistanceInKm = walkDistance * MILE_TO_KM;

        System.out.printf("You would be able to walk %.2f km.\n", walkDistanceInKm);


        /*
         * Question 1d (Number of Steps)
         * Purpose: Calculate the number of steps.
         */
        final double STRIDE_RATIO = 0.43;
        final double CM_TO_METER = 0.01;
        final double KM_TO_METER = 1000;

        // Convert user height to meter.
        double userHeight = 175;
        double userHeightInM = userHeight * CM_TO_METER;

        double strideLength = userHeightInM * STRIDE_RATIO; // stride length = heigiht * 0.43
        double walkDistanceInM = walkDistanceInKm * KM_TO_METER; // convert walking distance in kilometer from 1c to meter
        
        // 1 step === stride length to get total user steps. 
        int userSteps = (int)(walkDistanceInM / strideLength); // Casted step value to integer data type.

        System.out.println("In that time, you will take " + userSteps + " steps.");


        /*
         * Question 1e (How many laps)
         * Purpose: Calculate the number of laps it will take to burn the caloires.
         */
        double trackDiameter = 200;

        // Calcualte cirmcumference of the circular track
        double oneLapInM = Math.PI * trackDiameter; // circumference = 2πr = πd since d = 2r
        double numOfLapsWalked = walkDistanceInM / oneLapInM; // Calculate number of laps

        System.out.printf("You will take %.2f laps around the track.\n", numOfLapsWalked);
        System.out.println("Program termniated normally.");
    }
}
