import java.awt.Point;
import java.util.HashMap;

public class Board {
    private final int rows;
    private final int cols;
    private final Tile[][] board;
    public final HashMap<Integer, Point> numMap; // change to private
    private final static int BLANK = 0;

    // default constructor
    public Board() {
        this.rows = 3;
        this.cols = 3;
        this.board = new Tile[3][3];
        this.numMap = new HashMap<>();

        fillBoard();
        fillSolutions();
    }
    // custom constructors
    public Board(int rows, int cols) {
        // min reqs for dimensions
        if (rows < 2 || cols < 2) {
            throw new IllegalArgumentException("Dimensions smaller than 2 not permitted.");
        }

        this.rows = rows;
        this.cols = cols;
        this.board = new Tile[rows][cols];
        this.numMap = new HashMap<>();

        fillBoard();
        fillSolutions();
    }
    
    // randomly assign stuff in Board
    private void fillBoard() {
        int num = (int) (Math.random()*(this.rows*this.cols));
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {

                // if rand int already in the map, pick until a new one is found.
                while (this.numMap.containsKey(num)) {
                    num = (int) (Math.random()*(this.rows*this.cols));                
                }
                this.board[r][c] = new Tile(r, c, num); 
                this.numMap.put(num, new Point(r,c));
            }

        }
    }

    // fillSolutions sets the correct values for each board tile
    private void fillSolutions() {
        int num = 1;
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {
                Tile tile = this.board[r][c];

                if (r == this.rows-1 && c == this.cols-1) { // if last tile, put blank
                    tile.setCorrectValue(BLANK);
                    
                } else {
                    tile.setCorrectValue(num);
                    num++;
                }
            }
        }
    }

    // slideTile allows users to slide the board tiles
    public void slideTile(int num) {
        // check num in board
        if (!this.numMap.containsKey(num)) {
            throw new IllegalArgumentException("Number out of bounds. Try again!"); 
        }

        // check num is next to blank tile
        Point numPoint = this.numMap.get(num);
        Point blankPoint = this.numMap.get(BLANK);
        if (!(numPoint.x == blankPoint.x && Math.abs(numPoint.y-blankPoint.y)==1) &&
            !(Math.abs(numPoint.x-blankPoint.x)==1 && numPoint.y == blankPoint.y)) {
            throw new IllegalArgumentException("Number "+ num+ " not adjacent to blank tile");
        }

        // change numMap
        Point temp = this.numMap.get(num);
        this.numMap.put(num, this.numMap.get(BLANK));
        this.numMap.put(BLANK, temp);

        // change board
        int numRow = this.numMap.get(num).x;
        int numCol = this.numMap.get(num).y;
        this.board[numRow][numCol].setCurrentValue(num);

        int blankRow = this.numMap.get(BLANK).x;
        int blankCol = this.numMap.get(BLANK).y;
        this.board[blankRow][blankCol].setCurrentValue(BLANK);
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
                    Tile tile = this.board[r][c];
                    int num = tile.getCurrentValue();

                    // make the number bold if the position is correct
                    String numStr = String.valueOf(num);
                    if (tile.checkCorrect()) {
                        numStr = boldInt(num);
                    }

                    if (num == 0) {
                        boardString += "   | ";
                    }else if (num >= 10){ // add less spaces for double digits
                        boardString += numStr +" | ";
                    }
                    else { // add more spaces for single digits
                        boardString += numStr +"  | ";
                    }
                }
            }
        
        }
        boardString += "\n";

        return boardString;
    }
    // boldInt is a helper method for toString
    // to bold numbers when the number is in the right place
    private static String boldInt(int n) {
        String boldOn = "\u001B[1m";
        String reset = "\u001B[0m";

        return boldOn + n + reset;
    }
    
    // check whether the current board has the correct values
    public boolean checkSolved() {
        boolean isSolved = true;

        breakAll:
        for (int r = 0; r < this.rows; r++) {
            for (int c = 0; c < this.cols; c++) {

                Tile tile = this.board[r][c];

                if (!tile.checkCorrect()) {
                    isSolved = false;
                    break breakAll;
                }
            }
        }
        return isSolved;
    }

    // GETTERS
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }
}
