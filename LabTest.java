public class LabTest {
    public static void main(String[] args) {
        int[] originalArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        // Print the original array
        System.out.println("Original Array: ");
        printArray(originalArray);

        // Reverse elements from index 1 to 5
        int[] reversedArray = reverseSubarray(originalArray, 1, 5);

        // Print the reversed array
        System.out.println("Reversed Array from index 1 to 5: ");
        printArray(reversedArray);
    }

    // Method to reverse elements from startIndex to endIndex (inclusive) in an array
    private static int[] reverseSubarray(int[] array, int startIndex, int endIndex) {
        int[] reversedArray = new int[array.length];

        // Copy elements before the subarray
        for (int i = 0; i < startIndex; i++) {
            reversedArray[i] = array[i];
        }

        // Reverse the subarray
        for (int i = startIndex, j = endIndex; i <= endIndex; i++, j--) {
            reversedArray[i] = array[j];
        }

        // Copy elements after the subarray
        for (int i = endIndex + 1; i < array.length; i++) {
            reversedArray[i] = array[i];
        }

        return reversedArray;
    }

    // Method to print the elements of an array
    private static void printArray(int[] array) {
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}