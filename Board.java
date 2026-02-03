import java.awt.Point;
import java.util.HashMap;

public class Board {
    private final int rows;
    private final int cols;
    private final int[][] board;
    public final HashMap<Integer, Point> numMap;
    public final static int BLANK = 0;

    // default constructor
    public Board() {
        this.rows = 3;
        this.cols = 3;
        this.board = new int[3][3];
        this.numMap = new HashMap<>();

        // random nums
        fillBoard();
    }
    // custom constructorsrc 
    public Board(int rows, int cols) {
        // mac / min reqs for dims
        if (rows < 2 || cols < 2) {
            throw new IllegalArgumentException("Dimensions smaller than 2 not permitted.");
        }

        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
        
        this.numMap = new HashMap<>();

        fillBoard();
    }
    // randomly assign stuff in Board
    private void fillBoard() {
        int num = (int) (Math.random()*(this.rows*this.cols)); // change later to random
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {

                // if rand int already in the map, pick until a new one is found.
                while (this.numMap.containsKey(num)) {
                    num = (int) (Math.random()*(this.rows*this.cols));                }
                this.board[r][c] = num; // change later to random
                this.numMap.put(num, new Point(r,c));
            }

        }
    }
    public void slideTile(int num) {
        if (num <= 0 || num >= this.getRows()*this.getCols()) {
            throw new IllegalArgumentException("Number out of bounds. Try again!"); 
        }
        Point numPoint = numMap.get(num);
        Point blankPoint = numMap.get(BLANK);
        if (!(numPoint.x == blankPoint.x && Math.abs(numPoint.y-blankPoint.y)==1) &&
            !(Math.abs(numPoint.x-blankPoint.x)==1 && numPoint.y == blankPoint.y)) {
            throw new IllegalArgumentException("Number "+ num+ " not adjacent to blank tile");
        }

        // change numMap
        Point temp = numMap.get(num);
        numMap.put(num, numMap.get(BLANK));
        numMap.put(BLANK, temp);

        // change board
        int numRow = numMap.get(num).x;
        int numCol = numMap.get(num).y;
        board[numRow][numCol] = num;

        int blankRow = numMap.get(BLANK).x;
        int blankCol = numMap.get(BLANK).y;
        board[blankRow][blankCol] = BLANK;
    }

    // when lined up right, sometimes this doesn't work.
    // check if board is in increasing order
    public boolean checkSolved() {
        boolean isSolved = true;
        for (int r = 0; r < this.getRows(); r++) {
            for (int c = 0; c < this.getCols(); c++) {
                int prev;
                if (r == 0 && c == 0) { // first in table
                    continue;
                }
                else if (c == 0) {
                    prev = this.board[r-1][this.getCols()-1]; // last in row
                } else {
                    prev = this.board[r][c-1];
                }
                
                int cur = this.board[r][c];
                if (cur == BLANK) { // if blank
                    if (c != this.getCols()-1 || r != this.getRows()-1) {
                        isSolved = false;
                        break;
                    }
                }
                else if (prev >= cur) { // nondecreasing
                    isSolved = false;
                    break;
                }
            }
        }
        return isSolved;
    }

    @Override
    public String toString() {
        String boardString = "";

        for (int r = 0; r <= rows; r++) {
            boardString += "\n +";
            // print border
            for (int c = 0; c < cols; c++) {
                boardString += "----+";
            }

            // print nums
            if (r < rows){
                boardString += "\n | ";
                for (int c = 0; c < cols; c++) {
                    int num = board[r][c];
                    if (num == 0) {
                        boardString += "   | ";
                    }else if (num >= 10){
                        boardString += num +" | ";
                    }
                    else {
                        boardString += num +"  | ";
                    }
                }
            }
            
        
        }
        boardString += "\n";
        /*
        bold numbers when the number is in the right place:
        // ANSI escape code for bold text: \u001B[1m or \033[0;1m
        String boldOn = "\u001B[1m";
        // ANSI escape code to reset formatting: \u001B[0m
        String reset = "\u001B[0m";
         System.out.println("This is " + boldOn + "bold" + reset + " text.")
         */

        return boardString;
    }
    
    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
}
