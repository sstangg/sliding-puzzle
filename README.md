By: Sophia Tang

# SLIDING PUZZLE GAME

### To run this game in the terminal:
1. javac *.java
2. java Main

### File information:
1. Main.java
    - controls the game flow & control
    - contains the main() method

2. Board.java
    - creates & stores the game board data
    - offers method interface that allows Main to manipulate the data

3. Player.java
    - represent Player information (name, pronouns)

### Input / Output Example

Current board:  
+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;&nbsp;|&nbsp; 3 &nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 5 &nbsp;|&nbsp; 6 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 7 &nbsp;|&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;|&nbsp; 8 &nbsp;|  
+---+---+---+  

Player, which tile do you want to slide to the empty space?  
**Input:** 8 

**Output:**  
+---+---+---+  
|&nbsp;&nbsp; 1 &nbsp;&nbsp;|&nbsp; 2 &nbsp;|&nbsp; 3 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 4 &nbsp;|&nbsp; 6 &nbsp;|&nbsp; 5 &nbsp;&nbsp;|  
+---+---+---+  
|&nbsp;&nbsp; 7 &nbsp;|&nbsp; 8 &nbsp;| &nbsp; &nbsp;&nbsp;&nbsp; |  
+---+---+---+  
Congrats, you won!  
Game ended. Play again? (y/n)  
**Input:** n  

**Output:**
Game ended. Bye {player-name}!