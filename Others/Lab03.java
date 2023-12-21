import java.util.Scanner;

class Lab3 {
    public static void main(String[] args) {

        Scanner keyIn = new Scanner(System.in);

        System.out.print("Please enter two numbers, separated by a space: ");

        String firstString = keyIn.next();
        String secondString = keyIn.next();

        double first = Double.parseDouble(firstString);
        double second = Double.parseDouble(secondString);

        double answer = Math.pow(first, second);

        System.out.printf("The number %5.2f to the power %5.2f is %5.2f\n", first, second, answer);

        System.out.print("\nPlease enter your name: ");
        String name = keyIn.next();

        System.out.println("Your name starts with " + name.charAt(0) + " and ends with "
                + name.charAt(name.length() - 1));

        keyIn.close();

        System.out.println("Program terminated normally.");
    }
}
