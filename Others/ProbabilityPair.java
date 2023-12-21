public class ProbabilityPair {
    public static void main(String[] args) {
        int numberOfDice = 4;
        int numberOfSides = 6;
        int pairCount = 0;
        double totalRolls = Math.pow(numberOfSides, numberOfDice);

        for (int die1 = 1; die1 <= numberOfSides; die1++) {
            for (int die2 = 1; die2 <= numberOfSides; die2++) {
                for (int die3 = 1; die3 <= numberOfSides; die3++) {
                    for (int die4 = 1; die4 <= numberOfSides; die4++) {
                        // Check for pairs
                        if (die1 == die2 || die1 == die3 || die1 == die4 || die2 == die3 || die2 == die4 || die3 == die4) {
                            pairCount++;
                        }
                    }
                }
            }
        }

        double probablilty = pairCount / totalRolls;

        System.out.printf("Probability: %.2f ", probablilty);
    }
}

