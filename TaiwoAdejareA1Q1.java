/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 1
* QUESTION: Question 1
*
* PURPOSE: Write a Java program that prints one line of output to the console.
*/


public class TaiwoAdejareA1Q1 {
    public static void main(String[] args) {
        /*
         * Question 1a (Calculate Intake)
         * Purpose: Calculate the total calories taken
         * URL: https://www.webmd.com/diet/healthtool-food-calorie-counter
         */

        // Selected food item name from the url above
        String foodItem = "Brownie, Chocolate Walnut, Frozen, Jenny Craig";
        int caloriesPerItem = 180; // Number of calories per food item
        int numOfFoodItem = 30; // Number of food Item eaten.

        int totalCalories = caloriesPerItem * numOfFoodItem; // Calculate total calories taken.
        System.out.println(numOfFoodItem + " " + foodItem + " contain a total of " + totalCalories + " calories.");

        /*
         * Question 1b (Burn it off)
         * Purpose: Calculate how to burn the calories off.
         */
        final double KG_TO_LBS = 2.20462; // Conersion rate of kilogram to pounds(lbs)
        final int HOUR_TO_MIN = 60; // Conversion rate of hour(hr) to minutes(min)

        // Weight range in pounds(lbs)
        double weight1 = 130; // x1
        double weight2 = 155; // x2

        // Calories burned per hours(calories/hr)
        double caloriesPerHour1 = 207; // y1
        double caloriesPerHour2 = 246; // y2

        // Burn rate in calories/hr
        double burnRate = (caloriesPerHour2 - caloriesPerHour1) / (weight2 - weight1); // Slope = m = (y2 - y1) / (x2 - x1)
        double yIntercept = caloriesPerHour1 - (burnRate * weight1); // yIntercept = b = y1 - (m * x1)

        // Convert user weight to pounds(lbs)
        double userWeightInKg = 95; // User weight in kilogram(kg)
        double userWeightInLbs = userWeightInKg * KG_TO_LBS; // User weight in pounds(lbs)
        System.out.printf("You are " + userWeightInKg + " Kilograms or %.2f pounds.\n", userWeightInLbs);

        // Calculate user's burn rate
        double userBurnRate = (burnRate * userWeightInLbs) + yIntercept; // user burn rate in calories/hr = y = mx + b
        double userBurnRateInMin = userBurnRate / HOUR_TO_MIN; // user burn rate in calories/min;
        System.out.printf("When walking, you consume %.2f calories per minute.\n", userBurnRateInMin);

        // Calculate user's walking time to burn calories in Q1a
        double walkTimeInMin = totalCalories / userBurnRateInMin;
        System.out.printf("You would be able to walk %.2f minutes if you ate " + numOfFoodItem + " " + foodItem + "\n", walkTimeInMin);

        /*
         * Question 1c (How Far Can You Walk)
         * Purpose: Calculate how far can the user walk.
         */
        final double MILE_TO_KM = 1.60934; // Conversion rate of Miles ot Kilometer

        double walkingSpeed = 3.0; // Walking speed in miles/hr

        double walkTimeInHr = walkTimeInMin / HOUR_TO_MIN; // Convert walking time in 1b which is in minutes to hour

        double walkDistance = walkingSpeed * walkTimeInHr; // Calculate walking distance in miles
        double walkDistanceInKm = walkDistance * MILE_TO_KM; // Convert walking distance in miles to Kilometer
        System.out.printf("You would be able to walk %.2f km.\n", walkDistanceInKm);

        /*
         * Question 1d (Number of Steps)
         * Purpose: Calculate the number of steps.
         */
        final double STRIDE_RATIO = 0.43; // Stride ratio
        final double CM_TO_METER = 0.01; // Converstion rate from Centimeter to meter
        final double KM_TO_METER = 1000; // Conversion rate of Kilometer to meter

        double userHeight = 30; // User's given height in centimeter
        double userHeightInM = userHeight * CM_TO_METER; // Convert user height to meter.

        double strideLength = userHeightInM * STRIDE_RATIO; // stride length = heigiht * 0.43
        double walkDistanceInM = walkDistanceInKm * KM_TO_METER; // coonvert walking distance in kilometer from 1c to meter
        // 1 step === stride length to get total user steps. 
        int userSteps = (int)(walkDistanceInM / strideLength); // Casted step value to integer data type.

        System.out.println("In that time, you will take " + userSteps + " steps.");

        /*
         * Question 1e (How many laps)
         * Purpose: Calculate the number of laps it will take to burn the caloires.
         */
        double trackDiameter = 200; // Diameter of the track

        // Calcualte cirmcumference of the circular track
        double oneLapInM = Math.PI * trackDiameter; // circumference = 2πr = πd since d = 2r
        double numOfLapsWalked = walkDistanceInM / oneLapInM; // Calculate number of laps

        System.out.printf("You will take %.2f laps around the track.\n", numOfLapsWalked);
        System.out.println("Program termniated normally");
    }
}
