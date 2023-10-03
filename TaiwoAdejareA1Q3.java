import java.util.Scanner;

public class TaiwoAdejareA1Q3 {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        
        final int TIME_RATIO = 60;
        final double KM_TO_MILE = 0.621371; // Conversion rate of Kilometer to Mile

        System.out.print("Enter the race distance (in kilometres): ");
        double raceDistance = scnr.nextDouble();

        System.out.print("Enter your finish time (as hh:mm:ss). Include zeros if applicable: ");
        String finishTime = scnr.next();

        int finishTimeHr = Integer.parseInt(finishTime.substring(0, 2));
        int finishTimeMin = Integer.parseInt(finishTime.substring(3, 5));
        int finishTimeSec = Integer.parseInt(finishTime.substring(6, 8));

        // Convert finish time to sec
        double finishTimeInMin = (finishTimeHr * TIME_RATIO) + finishTimeMin + (finishTimeSec / (double)TIME_RATIO);

        double timePerKmMin = finishTimeInMin / raceDistance;
        double timePerKmSec = (timePerKmMin % (int)timePerKmMin) * TIME_RATIO;
        String timePerKm = String.format("%d:%.1f", (int)timePerKmMin, timePerKmSec);

        double raceDistanceInMile = raceDistance * KM_TO_MILE;
        double timePerMileMin = finishTimeInMin / raceDistanceInMile;
        double timePerMileSec = (timePerMileMin % (int)timePerMileMin) * TIME_RATIO;
        String timePerMile = String.format("%d:%.1f", (int)timePerMileMin, timePerMileSec);


        System.out.println("\nYour race pace was " + timePerKm + " per km or " + timePerMile + " per mile.");
        
        System.out.print("\nEnter your 1-mile time, to the nearest second, as mm:ss. Include zeros if applicable: ");
        String oneMileTime = scnr.next();

        double trialDistance = 1;
        int trialMin = Integer.parseInt(oneMileTime.substring(0, 2));
        int trialSec = Integer.parseInt(oneMileTime.substring(3, 5));
        double trialTimeInMin = trialMin + (trialSec / (double)TIME_RATIO);

        // 5km 
        double distance1 = 5;
        double distance1InMile = distance1 * KM_TO_MILE;
        double predictedTimeInMin = (distance1InMile / trialDistance) * trialTimeInMin * 1.05;


        double distance2 = 5;
        double distanceInMile = distance2 * KM_TO_MILE;
        double predictedTimeInMin = (distanceInMile / trialDistance) * trialTimeInMin * 1.05;



    }
}
