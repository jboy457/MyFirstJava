class Week8CheckerboardDemo{
    public static void main(String[] args){

        final int ROWS = 10;
        final int COLS = 5;

        for (int row=1; row <= ROWS; row++){  //for each row in the pattern
            for (int col=1; col <= COLS; col++){  //for each column in the current row
                // if ((row+col) % 2 == 0 ){
                    //row+col is even
                    System.out.print("X");
                // }
                // else{
                    //row+col is odd
                    // System.out.print(" ");
                // }
            }
            System.out.println();
        }
    }
}