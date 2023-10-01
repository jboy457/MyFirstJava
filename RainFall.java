public class RainFall {
    public static void main(String[] args) {
        final int COLLECTION_AREA = 100;
        final int LAKE_AREA = 2;
        final int KM_TO_M = 1000;

        int raiseLevel = 1; // level at which you want to raise the lake

        int lakeAreaInMeter = 2 * KM_TO_M;
        int volumeNeeded = lakeAreaInMeter * raiseLevel;

        double collectionVolume = COLLECTION_AREA;
    }
}
