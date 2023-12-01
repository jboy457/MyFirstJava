import java.util.Scanner;

public class TaiwoAdejareA5Q1 {
    final static int SIZE = 1000;

    static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        String[] ACTIONS = { "Insert", "Delete", "Search", "Update", "Statistics", "Exit" };

        int pointer = 0;
        int currentId = 0;
        int[] IDs = new int[SIZE];
        String[] names = new String[SIZE];
        String[] emails = new String[SIZE];
        String[] phoneNumbers = new String[SIZE];

        int selectedOption = getValidInput(ACTIONS);

        // Keep request for action until
        while (selectedOption != ACTIONS.length) {
            switch (selectedOption) {
                case 1:
                    insertEmployee(IDs, names, emails, phoneNumbers, pointer, currentId);
                    pointer++;
                    currentId++;
                    break;
                case 2:
                    boolean isDeleted = deleteEmployee(IDs, names, emails, phoneNumbers, pointer);
                    if (isDeleted) {
                        pointer--;
                    }
                    break;
                case 3:
                    searchEmployee(IDs, names, emails, phoneNumbers, pointer);
                    break;
                case 4:
                    updateEmployee(IDs, names, emails, phoneNumbers, pointer);
                    break;
                case 5:
                    getAllEmployees(IDs, names, emails, phoneNumbers, pointer);
                    break;
                default:
                    break;
            }
            selectedOption = getValidInput(ACTIONS);
        }
        scnr.close();
        System.out.println("\n############ Terminated the program ##############");
    }

    public static void updateEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber,
            int pointer) {
        System.out.println("\n############## Update An Employee ################\n");
        System.out.print("Enter the ID to update employee: ");
        String id = scnr.nextLine();

        boolean isValidId = isStrAnInt(id);

        if (!isValidId) {
            System.out.println("Invalid ID");
            return;
        }

        int employeeId = Integer.parseInt(id);
        int employeeIndex = findEmployeeById(employeeId, IDs, pointer);

        if (employeeIndex == -1) {
            System.out.println("ID is not found");
            return;
        }

        String validName = getValidName();
        String validEmail = getValidEmail();
        String validPhone = getValidPhone();

        names[employeeIndex] = validName;
        emails[employeeIndex] = validEmail;
        phoneNumber[employeeIndex] = validPhone;

        System.out.printf("\n################### The employee with the ID %d is updated ###############\n", employeeId);
    }

    public static boolean deleteEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber,
            int pointer) {
        boolean isDeleted = false;
        System.out.println("\n############## Delete An Employee ################\n");
        System.out.print("Enter the ID to delete: ");
        String id = scnr.nextLine();

        boolean isValidId = isStrAnInt(id);

        if (!isValidId) {
            System.out.println("Invalid ID");
            return isDeleted;
        }

        int employeeId = Integer.parseInt(id);
        int employeeIndex = findEmployeeById(employeeId, IDs, pointer);

        if (employeeIndex == -1) {
            System.out.println("ID is not found");
            return isDeleted;
        }

        removeIntItem(IDs, employeeIndex, pointer);
        removeStrItem(names, employeeIndex, pointer);
        removeStrItem(emails, employeeIndex, pointer);
        removeStrItem(phoneNumber, employeeIndex, pointer);
        isDeleted = true;

        System.out.printf("\n#### Successfully deleted employee with ID %d ####\n", employeeId);
        return isDeleted;
    }

    public static void removeIntItem(int[] arr, int removeIndex, int pointer) {
        int[] result = new int[SIZE];
        int resultIndex = 0;
        for (int i = 0; i < pointer; i++) {
            if (i == removeIndex) {
                continue;
            }

            result[resultIndex++] = arr[i];
        }

        for (int i = 0; i < pointer; i++) {
            arr[i] = result[i];
        }
    }

    public static void removeStrItem(String[] arr, int removeIndex, int pointer) {
        String[] result = new String[SIZE];
        int resultIndex = 0;
        for (int i = 0; i < pointer; i++) {
            if (i == removeIndex) {
                continue;
            }

            result[resultIndex++] = arr[i];
        }
        for (int i = 0; i < pointer; i++) {
            arr[i] = result[i];
        }
    }

    public static void searchEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer) {
        System.out.println("\n################ Search An Employee ################\n");
        System.out.print("Enter the ID to search: ");
        String id = scnr.nextLine();

        boolean isValidId = isStrAnInt(id);

        if (!isValidId) {
            System.out.println("Invalid ID");
            return;
        }

        int employeeId = Integer.parseInt(id);
        int employeeIndex = findEmployeeById(employeeId, IDs, pointer);

        if (employeeIndex == -1) {
            System.out.println("ID is not found");
            return;
        }
        printEmployeeTH();
        printEmployeeTD(IDs[employeeIndex], names[employeeIndex], phoneNumber[employeeIndex], emails[employeeIndex]);
    }

    public static int findEmployeeById(int employeeId, int[] employeeIds, int pointer) {
        int index = -1;
        // Check if id exist using binary search
        int left = 0, right = pointer - 1;
        int middle = (left + right - 1) / 2;
        while (left <= right) {
            if (employeeIds[middle] < employeeId) {
                left = middle + 1;
            } else if (employeeIds[middle] == employeeId) {
                index = middle;
                break;
            } else {
                // ignore right side
                right = middle - 1;
            }
            middle = (left + right) / 2;
        }

        // if (left > right) {

        // }
        return index;
    }

    public static void getAllEmployees(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer) {
        System.out.println("\n######################## Records ######################\n");

        printEmployeeTH();
        for (int i = 0; i < pointer; i++) {
            printEmployeeTD(IDs[i], names[i], phoneNumber[i], emails[i]);
        }

        System.out.printf("\n############### Total existing employees = %d ################\n", pointer);
    }

    public static void printEmployeeTH() {
        System.out.printf("%s\t%s\t\t\t%s\t\t%s\n", "ID", "Name", "PhoneNumber", "Email");
    }

    public static void printEmployeeTD(int ID, String name, String phoneNumber, String email) {
        System.out.printf("%s\t%s\t\t%s\t\t%s\n", ID, name, phoneNumber, email);
    }

    public static void insertEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer,
            int currentId) {
        System.out.println("\n############# Insert New Employee ################\n");
        String validName = getValidName();
        String validEmail = getValidEmail();
        String validPhone = getValidPhone();

        IDs[pointer] = currentId;
        names[pointer] = validName;
        emails[pointer] = validEmail;
        phoneNumber[pointer] = validPhone;

        System.out.printf("\n############ The employee with the ID %d is added ###########\n", currentId);
    }

    public static String getValidName() {
        String promptStr = "Enter the full name: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!nameInputValid(input)) {
            System.out.println("Please enter a valid full name");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    public static String getValidEmail() {
        String promptStr = "Enter the email address: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!emailInputValid(input)) {
            System.out.println("Please enter a valid email address");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    public static String getValidPhone() {
        String promptStr = "Enter the valid phone number: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!phoneInputValid(input)) {
            System.out.println("Please enter a valid phone number. It should be in this formart \"(XXX)XXX-XXXX\"");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    public static boolean nameInputValid(String name) {
        boolean isValid = false;
        for (int i = 0; i < name.length(); i++) {
            char tempChar = name.charAt(i);
            boolean characterAllowed = tempChar == '-' || tempChar == '\'' || Character.isAlphabetic(tempChar)
                    || Character.isWhitespace(tempChar);
            if (!Character.isDigit(tempChar) && characterAllowed) {
                isValid = true;
            } else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public static boolean phoneInputValid(String phone) {
        final int PHONE_LENGTH = 13;
        boolean isValid = false;

        if (phone.length() == PHONE_LENGTH) {
            for (int i = 0; i < phone.length(); i++) {
                char tempChar = phone.charAt(i);
                if ((i == 0 && tempChar == '(')) {
                    isValid = true;
                } else if (i == 4 && tempChar == ')') {
                    isValid = true;
                } else if (i == 8 && tempChar == '-') {
                    isValid = true;
                } else if (Character.isDigit(tempChar)) {
                    isValid = true;
                } else {
                    isValid = false;
                    break;
                }
            }
        }

        return isValid;
    }

    public static boolean emailInputValid(String email) {
        boolean isValid = false;
        boolean AtSymExist = false;
        boolean dotSymExist = false;

        for (int i = 0; i < email.length(); i++) {
            char tempChar = email.charAt(i);
            if (tempChar == '@') {
                AtSymExist = true;
            }
            if (tempChar == '.') {
                dotSymExist = true;
            }
        }

        if (AtSymExist && dotSymExist) {
            isValid = true;
        }

        return isValid;
    }

    public static String getActionInput(String[] actions) {
        System.out.println("\n#################### PhoneBook Software ########################\n");
        // Print actions
        for (int sn = 0; sn < actions.length; sn++) {
            System.out.printf("%d. %s\n", sn + 1, actions[sn]);
        }

        System.out.printf("\nPlease enter your option (1 to %d): ", actions.length);
        String userInput = scnr.nextLine();

        return userInput;
    }

    public static int getValidInput(String[] actions) {
        String userInput = getActionInput(actions);

        // Keep asking until valid input is entered.
        while (!optionInputValid(userInput, actions.length)) {
            System.out.printf("Input must be between 1 and %d\n\n", actions.length);
            userInput = getActionInput(actions);
        }

        // return option number in integer.
        return Integer.parseInt(userInput);
    }

    public static boolean optionInputValid(String option, int actionLength) {
        boolean isValid = false;

        isValid = isStrAnInt(option);

        if (isValid) {
            int intOption = Integer.parseInt(option);

            // Check if number inputed is less than action length.
            if (intOption > actionLength || intOption < 1) {
                isValid = false;
            }

        }

        return isValid;
    }

    public static boolean isStrAnInt(String str) {
        boolean isInt = false;
        // Check if input is valid digit
        for (int i = 0; i < str.length(); i++) {
            char tempChar = str.charAt(i);
            if (Character.isDigit(tempChar)) {
                isInt = true;
            } else {
                isInt = false;
                break;
            }
        }

        return isInt;
    }
}
