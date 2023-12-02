/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 5
* QUESTION: Question 1
*
* PURPOSE: Developing a phonebook [20 marks]
*/

import java.util.Scanner;

public class TaiwoAdejareA5Q1 {
    final static int SIZE = 1000; // Size of phone book array.
    static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        final int INSERT = 1;
        final int DELETE = 2;
        final int SEARCH = 3;
        final int UPDATE = 4;
        final int STATISTICS = 5;
        final int EXIT = 6;

        // List of phone book actions
        String[] ACTIONS = { "Insert", "Delete", "Search", "Update", "Statistics", "Exit" };

        int pointer = 0;
        int currentId = 0; // To keep track of next userId.

        // Phone book columns
        int[] IDs = new int[SIZE];
        String[] names = new String[SIZE];
        String[] emails = new String[SIZE];
        String[] phoneNumbers = new String[SIZE];

        // Get option selected by user.
        int selectedOption = getValidAction(ACTIONS);

        // Keep request for action until last option is selected = Quit.
        while (selectedOption != EXIT) {
            switch (selectedOption) {
                case INSERT:
                    insertEmployee(IDs, names, emails, phoneNumbers, pointer, currentId);
                    // Increase the pointer and currentId after insertion.
                    pointer++;
                    currentId++;
                    break;
                case DELETE:
                    boolean isDeleted = deleteEmployee(IDs, names, emails, phoneNumbers, pointer);
                    // Decrease only pointer if employee data is deleted with no error.
                    if (isDeleted) {
                        pointer--;
                    }
                    break;
                case SEARCH:
                    searchEmployee(IDs, names, emails, phoneNumbers, pointer);
                    break;
                case UPDATE:
                    updateEmployee(IDs, names, emails, phoneNumbers, pointer);
                    break;
                case STATISTICS:
                    getAllEmployees(IDs, names, emails, phoneNumbers, pointer);
                    break;
                default:
                    break;
            }
            selectedOption = getValidAction(ACTIONS);
        }
        scnr.close();
        System.out.println("\n############ Terminated the program ##############");
    }

    /*
     * "insertEmployee" method insert valid data into thier respective arrays.
     * 
     * @paramters {int[] IDs, String[] names, String[] emails, String[] phoneNumber,
     * int pointer, int currentId}
     * 
     * @returns void;
     */
    public static void insertEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer,
            int currentId) {
        System.out.println("\n############# Insert New Employee ################\n");

        // Get valid user details.
        String validName = getValidName();
        String validEmail = getValidEmail();
        String validPhone = getValidPhone();

        // Insert vavlid details into the thier repective arrays
        IDs[pointer] = currentId;
        names[pointer] = validName;
        emails[pointer] = validEmail;
        phoneNumber[pointer] = validPhone;

        System.out.printf("\n############ The employee with the ID %d is added ###########\n", currentId);
    }

    /*
     * "deleteEmployee" method delete data from thier respective arrays.
     * 
     * @paramters {int[] IDs, String[] names, String[] emails, String[] phoneNumber,
     * int pointer}
     * 
     * @returns void;
     */
    public static boolean deleteEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber,
            int pointer) {
        boolean isDeleted = false;
        System.out.println("\n############## Delete An Employee ################\n");
        System.out.print("Enter the ID to delete: ");
        String id = scnr.nextLine();

        int employeeIndex = getEmployeeIndex(id, IDs, pointer);

        if (employeeIndex >= 0) {
            removeIntItem(IDs, employeeIndex, pointer);
            removeStrItem(names, employeeIndex, pointer);
            removeStrItem(emails, employeeIndex, pointer);
            removeStrItem(phoneNumber, employeeIndex, pointer);
            isDeleted = true;

            System.out.printf("\n#### Successfully deleted employee with ID %s ####\n", id);
        }

        return isDeleted;
    }

    /*
     * "searchEmployee" method search employee data and prints from thier respective
     * arrays.
     * 
     * @paramters {int[] IDs, String[] names, String[] emails, String[] phoneNumber,
     * int pointer}
     * 
     * @returns void;
     */
    public static void searchEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer) {
        System.out.println("\n################ Search An Employee ################\n");
        System.out.print("Enter the ID to search: ");
        String id = scnr.nextLine();

        int employeeIndex = getEmployeeIndex(id, IDs, pointer);

        if (employeeIndex >= 0) {
            printEmployeeTH();
            printEmployeeTD(IDs[employeeIndex], names[employeeIndex], phoneNumber[employeeIndex],
                    emails[employeeIndex]);
        }
    }

    /*
     * "updateEmployee" method updates employee data
     * 
     * @paramters {int[] IDs, String[] names, String[] emails, String[] phoneNumber,
     * int pointer}
     * 
     * @returns void;
     */
    public static void updateEmployee(int[] IDs, String[] names, String[] emails, String[] phoneNumber,
            int pointer) {
        System.out.println("\n############## Update An Employee ################\n");
        System.out.print("Enter the ID to update employee: ");
        String id = scnr.nextLine();

        int employeeIndex = getEmployeeIndex(id, IDs, pointer);

        if (employeeIndex >= 0) {
            String validName = getValidName();
            String validEmail = getValidEmail();
            String validPhone = getValidPhone();

            names[employeeIndex] = validName;
            emails[employeeIndex] = validEmail;
            phoneNumber[employeeIndex] = validPhone;

            System.out.printf("\n################### The employee with the ID %s is updated ###############\n", id);
        }
    }

    /*
     * "getAllEmployees" method prints all employee data
     * 
     * @paramters {int[] IDs, String[] names, String[] emails, String[] phoneNumber,
     * int pointer}
     * 
     * @returns void;
     */
    public static void getAllEmployees(int[] IDs, String[] names, String[] emails, String[] phoneNumber, int pointer) {
        System.out.println("\n######################## Records ######################\n");

        printEmployeeTH();
        for (int i = 0; i < pointer; i++) {
            printEmployeeTD(IDs[i], names[i], phoneNumber[i], emails[i]);
        }

        System.out.printf("\n############### Total existing employees = %d ################\n", pointer);
    }

    /*
     * "getEmployeeIndex" method check if id is valid, exist and return the
     * employeeIndex
     * 
     * @paramters {String id, int[] IDs, int pointer}
     * 
     * @returns int employeeIndex;
     */
    public static int getEmployeeIndex(String id, int[] IDs, int pointer) {
        int employeeIndex = -1; // Not found Index
        boolean isValidId = isStrANumber(id);

        if (!isValidId) {
            System.out.println("Invalid ID");
        } else {
            int employeeId = Integer.parseInt(id);
            employeeIndex = findEmployeeById(employeeId, IDs, pointer);

            if (employeeIndex == -1) {
                System.out.println("ID is not found");
            }
        }

        return employeeIndex;
    }

    /*
     * "findEmployeeById" method check if employee id exist on the system.
     * 
     * @paramters {int employeeId, int[] employeeIds, int pointer}
     * 
     * @returns int index;
     */
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
        return index;
    }

    /*
     * "printEmployeeTH" method prints formatted table header
     * 
     * @paramters {}
     * 
     * @returns void;
     */
    public static void printEmployeeTH() {
        System.out.printf("%s\t%s\t\t\t%s\t\t%s\n", "ID", "Name", "PhoneNumber", "Email");
    }

    /*
     * "printEmployeeTD" method prints formatted table data
     * 
     * @paramters {}
     * 
     * @returns void;
     */
    public static void printEmployeeTD(int ID, String name, String phoneNumber, String email) {
        System.out.printf("%s\t%s\t\t%s\t\t%s\n", ID, name, phoneNumber, email);
    }

    /*
     * "getValidAction" method keeps requesting for action until a valid selection
     * is made.
     * 
     * @paramters {String[] actions}
     * 
     * @returns int (selectedAction)
     */
    public static int getValidAction(String[] actions) {
        String selectedAction = getActionInput(actions);

        // Keep asking until valid input is entered.
        while (!optionInputValid(selectedAction, actions.length)) {
            System.out.printf("Input must be between 1 and %d\n\n", actions.length);
            selectedAction = getActionInput(actions);
        }

        // return option number in integer.
        return Integer.parseInt(selectedAction);
    }

    /*
     * "getActionInput" method prints all phone book actions and gets selected
     * option.
     * 
     * @paramters {String[] actions}
     * 
     * @returns String (selectedAction)
     */
    public static String getActionInput(String[] actions) {
        System.out.println("\n#################### PhoneBook Software ########################\n");
        // Print actions
        for (int sn = 0; sn < actions.length; sn++) {
            System.out.printf("%d. %s\n", sn + 1, actions[sn]);
        }

        System.out.printf("\nPlease enter your option (1 to %d): ", actions.length);
        String selectedAction = scnr.nextLine();

        return selectedAction;
    }

    /*
     * "getValidName" method gets user to keep inputing thier name until a valid one
     * is entered.
     * 
     * @paramters {}
     * 
     * @returns String (input)
     */
    public static String getValidName() {
        String promptStr = "Enter the full name: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!validNameInput(input)) {
            System.out.println("Please enter a valid full name");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    /*
     * "getValidEmail" method gets user to keep inputing thier email until a valid
     * one is entered.
     * 
     * @paramters {}
     * 
     * @returns String (input)
     */
    public static String getValidEmail() {
        String promptStr = "Enter the email address: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!validEmailInput(input)) {
            System.out.println("Please enter a valid email address");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    /*
     * "getValidPhone" method gets user to keep inputing thier phone until a valid
     * one is entered.
     * 
     * @paramters {}
     * 
     * @returns String (input)
     */
    public static String getValidPhone() {
        String promptStr = "Enter the valid phone number: ";
        System.out.print(promptStr);
        String input = scnr.nextLine();

        while (!validPhoneInput(input)) {
            System.out.println("Please enter a valid phone number. It should be in this formart \"(XXX)XXX-XXXX\"");
            System.out.print(promptStr);
            input = scnr.nextLine();
        }

        return input;
    }

    /*
     * "validNameInput" method run validation logic on the name entered to check if
     * its a valid name
     * 
     * @paramters {String name}
     * 
     * @returns boolean (isValid)
     */
    public static boolean validNameInput(String name) {
        boolean isValid = false;
        for (int i = 0; i < name.length(); i++) {
            char tempChar = name.charAt(i);
            // Allowed Character for name include alphabets, whiteSpaces, Hypens('-') and
            // Single quotes(" ' ") e.g O'lein Borrow , Mac-Anthony Geek.
            boolean characterAllowed = tempChar == '-' || tempChar == '\'' || Character.isAlphabetic(tempChar)
                    || Character.isWhitespace(tempChar);

            // Name should not consist of numbers and only accept characters above.
            if (!Character.isDigit(tempChar) && characterAllowed) {
                isValid = true;
            } else {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    /*
     * "validEmailInput" method run validation logic on the email entered to check
     * if
     * its a valid email
     * 
     * @paramters {String email}
     * 
     * @returns boolean (isValid)
     */
    public static boolean validEmailInput(String email) {
        boolean isValid = false;

        // NOTE: I just made a simple logic to validate the email i.e an email should at
        // least contain @ and . symbols.
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

    /*
     * "validPhoneInput" method run validation logic on the phone entered to check
     * if its a valid phone number
     * 
     * @paramters {String phone}
     * 
     * @returns boolean (isValid)
     */
    public static boolean validPhoneInput(String phone) {
        // Expected phone number string length.
        final int PHONE_LENGTH = 13;
        boolean isValid = false;

        // If the length is valid then check each character for expected symbols or
        // digits.
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

    /*
     * "validPhoneInput" method run validation logic on the selected phonebook
     * option
     * 
     * @paramters {String option, int actionLength}
     * 
     * @returns boolean (isValid)
     */
    public static boolean optionInputValid(String option, int actionLength) {
        boolean isValid = false;

        isValid = isStrANumber(option);

        if (isValid) {
            int intOption = Integer.parseInt(option);

            // Check if number inputed is less than action length.
            if (intOption > actionLength || intOption < 1) {
                isValid = false;
            }

        }

        return isValid;
    }

    /*
     * "isStrANumber" method check if string can be parsed into an integer. i.e all
     * values within the string is a number.
     * 
     * @paramters {String str}
     * 
     * @returns boolean (isNumber)
     */
    public static boolean isStrANumber(String str) {
        boolean isNumber = false;
        // Check if input is valid digit
        for (int i = 0; i < str.length(); i++) {
            char tempChar = str.charAt(i);
            if (Character.isDigit(tempChar)) {
                isNumber = true;
            } else {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    /*
     * "removeIntItem" method remove an integer from an partially filled array
     * without reducing the size.
     * 
     * @paramters {int[] arr, int removeIndex, int pointer}
     * 
     * @returns void
     */
    public static void removeIntItem(int[] arr, int removeIndex, int pointer) {
        int[] result = new int[SIZE];
        int resultIndex = 0;
        // skip item from and put into a new array
        for (int i = 0; i < pointer; i++) {
            if (i == removeIndex) {
                continue;
            }

            result[resultIndex++] = arr[i];
        }

        // copy back into old array
        for (int i = 0; i < pointer; i++) {
            arr[i] = result[i];
        }
    }

    /*
     * "removeStrItem" method remove an String from an partially filled array
     * without reducing the size.
     * 
     * @paramters {String[] arr, int removeIndex, int pointer}
     * 
     * @returns void
     */
    public static void removeStrItem(String[] arr, int removeIndex, int pointer) {
        String[] result = new String[SIZE];
        int resultIndex = 0;
        // skip item from and put into a new array
        for (int i = 0; i < pointer; i++) {
            if (i == removeIndex) {
                continue;
            }

            result[resultIndex++] = arr[i];
        }
        // copy back into old array
        for (int i = 0; i < pointer; i++) {
            arr[i] = result[i];
        }
    }
}
