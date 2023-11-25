/*
* COMP 1010 SECTION A01
* INSTRUCTOR: Dr. Heather Matheson
* STUDENT NUMBER: 7980132
* ASSIGNMENT: Assignment 4
* QUESTION: Question 1
*
* PURPOSE: Adventure game, NetHack!
*/

import java.util.Random;
import java.util.Scanner;

public class TaiwoAdejareA4 {
    // Room Bounderies
    final static int MAX_HEIGHT = 10;
    final static int MAX_WIDTH = 10;
    final static int MIN_HEIGHT = 5;
    final static int MIN_WIDTH = 5;

    // Moves direction.
    final static char UP_MOVE = 'u';
    final static char DOWN_MOVE = 'd';
    final static char LEFT_MOVE = 'l';
    final static char RIGHT_MOVE = 'r';

    // Game Characters
    final static char OCTOTHORPS = '#'; // Border
    final static char GRID_BUG = 'x';
    final static char GOLD = '$';
    final static char HOLE = '^';
    final static char PLAYER = '@'; // Adventuerer
    final static char FLOOR = '.';

    // Game properties - Origin of Coordinates (Top-left Corner (0,0) , Bottom-right
    // Corner (roomWidth - 1, roomHeight - 1))
    static int x_hole, y_hole, x_gold, y_gold, x_bug, y_bug, x_player, y_player; // coordinates for gold, bug, player
    static int roomHeight, roomWidth; // Generated Random room height and width within the range.
    static boolean goldPicked = false;
    static boolean endGame = false;

    // Total Steps
    static int totalSteps = 0; // Total steps moved by player
    static int totalInputs = 0; // Total input made by player
    static int stepsLeft = 0; // Queued steps.

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random();

        // Manages the game.
        playGame(scnr, rand);
    }

    /*
     * "playGame" method should start the game and keep the game active until
     * endGame condition is met.
     * 
     * @paramters {Scanner scnr, Random random}
     * 
     * @returns void.
     */
    public static void playGame(Scanner scnr, Random random) {
        System.out.println("Welcome to the dungeon!");
        // Generates inital random coordinate for game enviroment.
        generateRoom(random);

        // Print room with inital values.
        printRoom();

        // Enter Game
        while (!endGame) {
            String playMoves = getUserMoves(scnr); // valid moves.

            // Increment number of valid input by player.
            totalInputs++;

            // Move player by each step afer pressing enter.
            movePlayer(playMoves, scnr, random);

            if (endGame) {
                System.out.println("Program terminated successfully!");
            }
        }

    }

    /*
     * "generateRoom" method should generate inital size of room btn room range and
     * coordinate for character in game.
     * 
     * @paramters {Random random}
     * 
     * @returns void.
     */
    public static void generateRoom(Random random) {
        // Create room size within the given range.
        roomWidth = random.nextInt(MIN_WIDTH, MAX_WIDTH + 1);
        roomHeight = random.nextInt(MIN_HEIGHT, MAX_HEIGHT + 1);

        // Create inital room coordinate within the room.
        x_player = random.nextInt(roomWidth);
        y_player = random.nextInt(roomHeight);

        // Generate a unique coordinate for character in the game.
        do {
            x_bug = random.nextInt(roomWidth);
            y_bug = random.nextInt(roomHeight);
        } while (x_bug == x_player && y_player == y_bug); // The grid bug and the adventurer are not placed in the same
                                                          // location initially

        do {
            x_gold = random.nextInt(roomWidth);
            y_gold = random.nextInt(roomHeight);
        } while ((x_gold == x_player && y_gold == y_player) ||
                (x_gold == x_bug && y_gold == y_bug)); // The adventurer and the gold are not placed in the same
                                                       // location initially

        do {
            x_hole = random.nextInt(roomWidth);
            y_hole = random.nextInt(roomHeight);
        } while ((x_hole == x_bug && y_hole == y_bug) ||
                (x_hole == x_gold && y_hole == y_gold)); // The gold and the hole are not placed in the same location
                                                         // initially
    }

    /*
     * "printRoom" method should always print step and room based on current state
     * values/coordinate of character.
     * 
     * @paramters {int steps}
     * 
     * @returns void.
     */
    public static void printRoom() {
        System.out.println("\nStep " + totalSteps + ":");
        // Print top border
        topOrBottomBorder(roomWidth);

        // Print room with characters.
        for (int y_axis = 0; y_axis < roomHeight; y_axis++) {
            System.out.print(OCTOTHORPS);
            for (int x_axis = 0; x_axis < roomWidth; x_axis++) {
                if (x_axis == x_bug && y_axis == y_bug) {
                    System.out.print(GRID_BUG);
                } else if (x_axis == x_player && y_axis == y_player) {
                    System.out.print(PLAYER);
                } else if (x_axis == x_gold && y_axis == y_gold && !goldPicked) {
                    System.out.print(GOLD);
                } else if (x_axis == x_hole && y_axis == y_hole) {
                    System.out.print(HOLE);
                } else {
                    System.out.print(FLOOR);
                }
            }
            System.out.println(OCTOTHORPS);
        }

        // Print bottom border.
        topOrBottomBorder(roomWidth);
    }

    /*
     * "topOrBottomBorder" method is used to print top and bottom border based on
     * room width
     * 
     * @paramters {int roomWidth}
     * 
     * @returns void.
     */
    public static void topOrBottomBorder(int roomWidth) {
        for (int i = 0; i < roomWidth + 2; i++) {
            System.out.print(OCTOTHORPS);
        }
        System.out.println();
    }

    /*
     * "getUserMoves" method get player to input thier moves and validate it.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns String {move} e.g u 8 or d 10.
     */
    public static String getUserMoves(Scanner scnr) {
        String move = getPlayerInput(scnr);

        // Request for player input until it gets a valid one.
        while (!isValidMove(move)) {
            System.out.println("Input is invalid! Should be in the format '<direction_char> <num_steps>'.\n");
            move = getPlayerInput(scnr);
        }
        return move;
    }

    /*
     * "getPlayerInput" method prompt the question, accept moves and return it for
     * validation.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns {String} inputedMove.
     */
    public static String getPlayerInput(Scanner scnr) {
        System.out.print("What are your next steps? <u|d|l|r> <#steps>: ");
        return scnr.nextLine();
    }

    /*
     * "isValidMove" method is to validate the player input and make sure it aligns
     * with the expected formart.
     * 
     * @paramters {String move}
     * 
     * @returns {boolean}.
     */
    public static boolean isValidMove(String move) {
        // Condtions for validity
        boolean directionValid = false;
        boolean hasSpace = false;
        boolean stepValid = false;

        for (int i = 0; i < move.length(); i++) {
            // Pass check if the first charcter matches with the expected game direction.
            if ((i == 0) && ((move.charAt(i) == UP_MOVE) ||
                    (move.charAt(i) == DOWN_MOVE) ||
                    (move.charAt(i) == LEFT_MOVE) ||
                    (move.charAt(i) == RIGHT_MOVE))) {
                directionValid = true;
            }

            // Pass check if theres a space after the game direction char.
            if ((i == 1) && Character.isWhitespace(move.charAt(i))) {
                hasSpace = true;
            }

            // Pass check if all character after the space is a number.
            if ((i > 1) && Character.isDigit(move.charAt(i)) && Integer.parseInt(move.substring(i, i + 1)) > 0) {
                stepValid = true;
            }

            // Fail test immidiately it fined any non-digit character after space i.e u 9a2
            if ((i > 1) && !Character.isDigit(move.charAt(i))) {
                stepValid = false;
                break;
            }
        }

        // Must pass all the three validity check.
        return directionValid && hasSpace && stepValid;
    }

    /*
     * "movePlayer" method is to move player towared the specified direction in
     * number of steps given.
     * 
     * @paramters {String move(valid move), Scanner scnr, Random random}
     * 
     * @returns void.
     */
    public static void movePlayer(String move, Scanner scnr, Random random) {
        char direction = move.charAt(0); // get direction from move (u/d/l/r)

        // Get number of steps and convert it into integer from spaceIndex.
        int spaceIndex = move.indexOf(" ");
        String num = move.substring(spaceIndex + 1, move.length());
        stepsLeft = Integer.parseInt(num);

        /*
         * Increment or decrement the coordinates of the player based on direction and
         * steps,and if its hits the wall truncate the steps left. i.e end number of
         * steps in queue.
         */
        do {
            switch (direction) {
                case UP_MOVE:
                    if (y_player > 0) {
                        y_player--;
                    } else {
                        stepsLeft = 0;
                    }
                    break;
                case DOWN_MOVE:
                    if (y_player < roomHeight - 1) {
                        y_player++;
                    } else {
                        stepsLeft = 0;
                    }
                    break;
                case LEFT_MOVE:
                    if (x_player > 0) {
                        x_player--;
                    } else {
                        stepsLeft = 0;
                    }
                    break;
                case RIGHT_MOVE:
                    if (x_player < roomWidth - 1) {
                        x_player++;
                    } else {
                        stepsLeft = 0;
                    }
                default:
                    break;
            }
            // After every move reduce the number of steps left and add to total steps.
            stepsLeft--;
            totalSteps++;

            // For every move taken by the player randomly change the location of the bug.
            moveBug(random);

            // Print room based on current state.
            printRoom();

            // check if the player is killed, hit the wall etc.
            checkGameStatus(scnr);
        } while (stepsLeft > 0);
    }

    /*
     * "moveBug" method is randomly move the bug by one step either up or down or
     * diagonally.
     * 
     * @paramters {Random random}
     * 
     * @returns void.
     */
    public static void moveBug(Random random) {
        int x_newBug = 0;
        int y_newBug = 0;

        // Choose whether to move it othogornal - true or diagonal - false
        boolean isOthogornal = random.nextBoolean();

        if (isOthogornal) {
            x_newBug = x_bug + moveBugDirection(random, 3);
            y_newBug = y_bug + moveBugDirection(random, 0);
        } else {
            x_newBug = x_bug + moveBugDirection(random, 0);
            y_newBug = y_bug + moveBugDirection(random, 0);
        }

        // Check if position is within room
        if (x_newBug >= 0 && x_newBug < roomWidth && y_newBug >= 0 && y_newBug < roomHeight) {
            // Update bug postion
            x_bug = x_newBug;
            y_bug = y_newBug;
        }
    }

    /*
     * "moveBugDirection" method is randomly return where the move should move (left, right, up or down.)
     * 
     * @paramters {Random random, int bounds}
     * 
     * @returns void.
     */
    public static int moveBugDirection(Random rand, int bounds) {
        final int MAX_BUG_STEP = 1;

        int direction;
        if (bounds != 0) {
            direction = rand.nextInt(bounds) - 1; // return a random value [-1, 0, 1]
        } else {
            if (rand.nextBoolean()) {
                direction = -MAX_BUG_STEP; // Move backwards
            } else {
                direction = MAX_BUG_STEP; // Move forwards
            }
        }
        return direction;
    }

    /*
     * "checkGameStatus" method is to constantly check game status after every move
     * done by the player.
     * 
     * @paramters {Scanner scnr}
     * 
     * @returns void.
     */
    public static void checkGameStatus(Scanner scnr) {
        // Check if player and bug are on teh same coordinate and end game if true.
        if (x_bug == x_player && y_bug == y_player) {
            System.out.printf("You were killed by the grid bug after %d inputs and %d steps!\n", totalInputs,
                    totalSteps);
            stepsLeft = 0;
            endGame = true;
        }

        // Check if player hit the wall.
        if (stepsLeft < 0) {
            System.out.println("You hit a wall!");
        }

        // check if gold and player are on the same coordinate.
        if ((x_gold == x_player && y_gold == y_player) || goldPicked) {
            System.out.println("Picked up gold.");
            goldPicked = true;
        }

        // check if gold has already been picked and player and hole is in the same
        // coordinate.
        if (goldPicked && (x_player == x_hole && y_player == y_hole)) {
            System.out.printf("Congratulations, you've escaped after %d inputs and %d steps!\n", totalInputs,
                    totalSteps);
            stepsLeft = 0;
            endGame = true;
        }

        // Show number of steps and press enter to continue the game.
        if (stepsLeft > 0) {
            System.out.println("Queued steps remaining: " + stepsLeft);
            System.out.print("Press <enter> to continue...");
            scnr.nextLine();
        }

    }

}
