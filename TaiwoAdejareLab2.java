public class TaiwoAdejareLab2 {
    public static void main(String[] args) {
        final double COST_PER_MINUTES = 1.2;
        final double SERVICE_CHARGE = 14; // Service Charge to be added
        final double MOWING_RATE = 1.3; // Mowing Rate

        double houseLength = 0; // Length of the house
        double houseWidth = 0; // Width of the house

        double totalLength = 10; // Length of the total Area
        double totalWidth = 30; // Width of the total Area

        // Calculate area of house and total area
        double houseArea = calcArea(houseLength, houseWidth);
        double totalArea = calcArea(totalLength, totalWidth);

        // Calculate area of the lawn
        double areaToLawn = calcLawnArea(totalArea, houseArea);

        // Calculate time to lawn tehe area in second and convert it to minutes
        double lawnTimeSec = areaToLawn / MOWING_RATE;
        double lawnTimeMin = secToMin(lawnTimeSec);

        // Calculate the cost and also adding service charge to it
        double amountToCharge = lawnTimeMin * COST_PER_MINUTES + SERVICE_CHARGE;

        System.out.printf("Amount to charge customer is %.2f", amountToCharge);

    }

    /*
     * This function is used to calculate the area
     * it get it value for the from process calling it
     * 
     * @return double calcArea
     * Multiply area and its and then divide all by 3 - V=AH/3
     */
    static double calcArea(double length, double width) {
        return length * width;
    }

    static double calcLawnArea(double totalArea, double occupiedArea) {
        return totalArea - occupiedArea;
    }

    static double secToMin(double seconds) {
        final int SEC_TO_MIN = 60;
        return seconds / SEC_TO_MIN;
    }
}
