public class Pyramid {
    public static void main(String[] args) {
        int pyramidBase = 230; // Base length of the pyramid
        int pyramidHeight = 146; // Height of the pyramid

        int pyramidArea = calcArea(pyramidBase, pyramidBase) // Calculate the base area of the pyramid

        int pyramidVolume = calcVolume(pyramidArea, pyramidHeight); // Calculate the volume of the pyramid

        System.out.print(pyramidVolume);
    }

    /*
     * This function is used to calculate the volume of pyramid
     * it get it value for the from process calling it
     * 
     * @return int volume
     * Multiply area and its height and then divide all by 3 - V=AH/3
     */
    static int calcVolume(int area, int height) {
        return (area * height) / 3;
    }

    /*
     * This function is used to calculate the area of pyramid base - square
     * it get it value for the from process calling it
     * 
     * @return int area
     * Multiply length and breath - A = L X B
     */
    static int calcArea(int length, int breath) {
        return length * breath;
    }
}
