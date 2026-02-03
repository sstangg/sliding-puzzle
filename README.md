By: Sophia Tang

SLIDING PUZZLE GAME

To run this game in the terminal:
1. javac *.java
2. java Main

File information:
1. Main.java
    - controls the game flow & control
    - contains the main() method

2. Board.java
    - creates & stores the game board data
    - offers method interface that allows Main to manipulate the data

3. Player.java
    - represent Player information (name, pronouns)

Input / Output Example

Current board:
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 5 | 6 |
+---+---+---+
| 7 |   | 8 |
+---+---+---+
Player, which tile do you want to slide to the empty space? 
Input: 8 

Output:
+---+---+---+
| 1 | 2 | 3 |
+---+---+---+
| 4 | 6 | 5 |
+---+---+---+
| 7 | 8 |   |
+---+---+---+
Congrats, you won!
Game ended. Play again? (y/n)
User input: n

Output:
Game ended. Bye <player-name>!