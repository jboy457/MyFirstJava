/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 1
* QUESTION: Question 3
*
* PURPOSE: Running Pace [7 points]
*/

import java.util.Scanner;

public class TaiwoAdejareA1Q3 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        /*
         * Question 3a (Calculate Race Pace)
         * Purpose: Calculate and print their pace per km and pace per mile, with each output in terms of minutes and seconds
         */
        final double TIME_RATIO = 60;
        final double KM_TO_MILE = 0.621371;

        System.out.print("Enter the race distance (in kilometres): ");
        double raceDistance = scnr.nextDouble();

        System.out.print("Enter your finish time (as hh:mm:ss). Include zeros if applicable: ");
        String finishTime = scnr.next();

        // Getting Hour, Min and Sec from the string hh:mm:ss using substring.
        int colonIndex1 = finishTime.indexOf(":"); // Get 1st the index of ":" in the time give to seperate min from sec.
        int colonIndex2 = finishTime.indexOf(":", colonIndex1 + 1); // Get the 2nd index of ":" in the time give to seperate min from sec.
        int finishTimeHr = Integer.parseInt(finishTime.substring(0, colonIndex1));
        int finishTimeMin = Integer.parseInt(finishTime.substring(colonIndex1 + 1, colonIndex2));
        int finishTimeSec = Integer.parseInt(finishTime.substring(colonIndex2 + 1, finishTime.length()));

        // Convert finish time to minutes by converting the hour to minutes and sec to minutes and summing it up.
        double finishTimeInMin = (finishTimeHr * TIME_RATIO) + finishTimeMin + (finishTimeSec / TIME_RATIO);

        // Calculate the time per Kilometer and covert it back to minutes and seconds
        double timePerKmMin = finishTimeInMin / raceDistance;
        double timePerKmSec = (timePerKmMin % (int)timePerKmMin) * TIME_RATIO;
        String timePerKm = String.format("%d:%.1f", (int)timePerKmMin, timePerKmSec);

        // Calculate the time per Mile and covert it back to minutes and seconds
        double raceDistanceInMile = raceDistance * KM_TO_MILE;
        double timePerMileMin = finishTimeInMin / raceDistanceInMile;
        double timePerMileSec = (timePerMileMin % (int)timePerMileMin) * TIME_RATIO;
        String timePerMile = String.format("%d:%.1f", (int)timePerMileMin, timePerMileSec); 

        System.out.println("\nYour race pace was " + timePerKm + " per km or " + timePerMile + " per mile.");
        
        /*
         * Question 3b (Predict Finish Times)
         * Purpose: Using that time, predict the time for the user to run 5 km, 10 km, a half marathon (13.1 miles), and a marathon (26.2 miles)
         */

        final double PREDICT_RATE = 1.08;

        System.out.print("\nEnter your 1-mile time, to the nearest second, as mm:ss. Include zeros if applicable: ");
        String trialTime = scnr.next();

        // Get Minutes and second from the trial time which is in string and convert all to minutes.
        double trialDistanceInMile = 1;
        int trialColonIndex = trialTime.indexOf(":");
        int trialMin = Integer.parseInt(trialTime.substring(0, trialColonIndex));
        int trialSec = Integer.parseInt(trialTime.substring(trialColonIndex + 1, trialTime.length()));
        double trialTimeInMin = trialMin + (trialSec / (double)TIME_RATIO);

        // Calculate prediction for 5km 
        double distance1InKm = 5;
        double distance1InMile = distance1InKm * KM_TO_MILE;
        double distance1TimeInMin = (distance1InMile / trialDistanceInMile) * trialTimeInMin * PREDICT_RATE; // Get time to complete 5km in minutes t2 = (d2 / d1) * t1 * 1.05
        int distance1Hr = (int)(distance1TimeInMin / TIME_RATIO); // Get hour(hh) from the time to complete 5km
        int distance1Min = (int)(distance1TimeInMin - (distance1Hr * TIME_RATIO)); // Get minutes(mm) which is the time left after removing the hours(hh)
        int distance1Sec = (int)((distance1TimeInMin % (distance1Min + (distance1Hr * TIME_RATIO))) * TIME_RATIO); // Get seconds (ss) which is the remainder after removing the minutes and hours.

        System.out.printf("\nYou predicted %.0f km time is %02d:%02d:%02d.\n", distance1InKm, distance1Hr, distance1Min, distance1Sec);
        
        // Repeated the same step for 5km.
        double distance2InKm = 10; // d2
        double distance2InMile = distance2InKm * KM_TO_MILE;
        double distance2TimeInMin = (distance2InMile / trialDistanceInMile) * trialTimeInMin * PREDICT_RATE;
        int distance2Hr = (int)(distance2TimeInMin / TIME_RATIO);
        int distance2Min = (int)(distance2TimeInMin - (distance2Hr * TIME_RATIO));
        int distance2Sec = (int)((distance2TimeInMin % (distance2Min + (distance2Hr * TIME_RATIO))) * TIME_RATIO);

        System.out.printf("You predicted %.0f km time is %02d:%02d:%02d.\n", distance2InKm, distance2Hr, distance2Min, distance2Sec);

        // Repeated the same step form above but i didnt have to change km to mile because prediction time in already in miles
        double distance3InMile = 13.1; // d2
        double distance3TimeInMin = (distance3InMile / trialDistanceInMile) * trialTimeInMin * PREDICT_RATE;
        int distance3Hr = (int)(distance3TimeInMin / TIME_RATIO);
        int distance3Min = (int)(distance3TimeInMin - (distance3Hr * TIME_RATIO));
        int distance3Sec = (int)((distance3TimeInMin % (distance3Min + (distance3Hr * TIME_RATIO))) * TIME_RATIO);

        System.out.printf("You predicted half marathon time is %02d:%02d:%02d.\n", distance3Hr, distance3Min, distance3Sec);

        // Repeated the same step form above but i didnt have to change km to mile because prediction time in already in miles
        double distance4InMile = 26.2; // d2
        double distance4TimeInMin = (distance4InMile / trialDistanceInMile) * trialTimeInMin * PREDICT_RATE;
        int distance4Hr = (int)(distance4TimeInMin / TIME_RATIO);
        int distance4Min = (int)(distance4TimeInMin - (distance4Hr * TIME_RATIO));
        int distance4Sec = (int)((distance4TimeInMin % (distance4Min + (distance4Hr * TIME_RATIO))) * TIME_RATIO);

        System.out.printf("You predicted marathon time is %02d:%02d:%02d.\n", distance4Hr, distance4Min, distance4Sec);

        scnr.close();
        System.out.println("\nProgram terminated normally.");
    }
}


