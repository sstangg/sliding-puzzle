import java.util.Scanner;

public class Main {
    private final Scanner scan;
    public Board board; // do i need to use this.scan or this.board if singleton only?
    private Player player;

    public Main() {
        scan = new Scanner(System.in);
    }
    public void run() {
        try {
            intialize();
            rules();

            boolean replay = true;
            while (replay) {
                startGame();
                gamePlay();
                replay = replay();
            }
            end();
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    private void intialize() {
        System.out.println("Welcome!\nWhat is your name?");
        String name = scan.next();
        System.out.println("Hello " + name + ". What are your pronouns? (she/he/they/etc.)");
        String pronouns = scan.next();
        player = new Player(name, pronouns);
    }  
    public void rules() {
        System.out.println("""
                           \nSLIDING PUZZLE GAME
                             In this game, a random shuffle puzzle will be generated for you to solve. 
                            
                           How to play:
                            - Each turn, type a tile value that is next to the blank space & press Enter to slide it into the blank space.
                            - The puzzle is solved when all tiles have been moved around such that 
                            the ordering of the tiles are ordered from LEAST to GREATEST from top LEFT to bottom RIGHT.
                            
                            Example:
                            An initial 3x3 puzzle could be:
                            +---+---+---+
                            | 8 | 3 | 2 |
                            +---+---+---+
                            | 4 | 1 | 5 |
                            +---+---+---+
                            | 6 |   | 7 |
                            +---+---+---+

                           The solution for a 3x3 puzzle is:
                            +---+---+---+
                            | 1 | 2 | 3 |
                            +---+---+---+
                            | 4 | 6 | 5 |
                            +---+---+---+
                            | 7 | 8 |   |
                            +---+---+---+

                            - Enter 'q' to quit any time.
                            - Enter 'r' to view these rules again.
                            """
        );
    }

    private void startGame() {
        System.out.println("""
                           The default board is 3x3. Would you like to use the default dimensions?
                           If not, you can customize the dimensions. (y/n)""");

        String answer = scan.next();
        boolean isDefault = answer.equals("y");
        if (!isDefault) {
            boolean invalidInput = false;
            int rows;
            int cols; 
            do {
                System.out.println("How many rows would you like the board to have? (max: y)\n");
                rows = scan.nextInt();  
                System.out.println("How many columns would you like the board to have? (max: y)\n");
                cols = scan.nextInt(); 

                try {
                    board = new Board(rows, cols);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try again!");
                    invalidInput = true;
                }
            } while (invalidInput);
            

            // question to confirm whether they want dimensions & option to exit?  
            System.out.println("Generating new board with dimensions " + rows + " x " + cols); 
            
            
        } else {
            // question to confirm whether they want dimensions & option to exit?  
            System.out.println("Generating new board with dimensions 3 x 3"); 
            board = new Board();
        }
        System.out.println(board.toString()); 
    }


    private void gamePlay() {
        boolean isSolved = false;
        while(!isSolved) {
            System.out.println(player.getName() + ", which tile do you want to slide to the empty space?"); 

            if (scan.hasNextInt()) { // valid number
                int num = scan.nextInt();
                try {   
                    move(num);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("Try again!");
                    continue;
                }
                isSolved = board.checkSolved();
            } else if (scan.next().equals("q")) { // quit
                break;
            } else if (scan.next().equals("r")) { // view directions
                rules();
            } else {
                System.out.println("Non-integers not accepted. Try again!"); 
            }
        }
        if (isSolved){ win();
        } else { quit(); }
    }
   
    private void move(int num) {
        try {
            board.slideTile(num);
            System.out.println(board.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void win() {
        System.out.println("Congrats, you won!"); 
    }
    private void quit() {
        System.out.println("You quit the game."); 
    }
    private boolean replay() {
        System.out.println("Game ended.\n Play again? (y/n)"); 

        boolean replay = false;
        boolean invalidInput = false;
        do {
            String input = scan.next(); 
            switch (input) {
                case "y" -> replay = true;
                case "n" -> {
                }
                default -> {
                    invalidInput = true;
                    //throw new IllegalArgumentException("Invalid input. Try again! (y/n)");
                    System.out.println("Invalid input. Try again! (y/n)"); // question: do i need to throw an error instead?
                }
            }
        } while (invalidInput);
        return replay;
        
    }
    private void end() {
        try (scan) {
            System.out.println("Game ended. Bye "+ player.getName()+ "!");
        }
    }
    public static void main(String[] args) {
        Main gc = new Main();
        gc.run();
        /* 

        gc.board = new Board(1,2);
        System.out.println(gc.board);
        gc.gamePlay();
        */
       
    }
    
}
