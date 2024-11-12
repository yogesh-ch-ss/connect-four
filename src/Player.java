import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private Grid grid;

    public Player(String name, char symbol, Grid grid) {
        this.name = name;
        this.symbol = symbol;
        this.grid = grid;
    }

    public String toString() {
        return "Player: " + this.name + " (" + this.symbol + ")";
    }

    public String getName() {
        return this.name;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public Grid getGrid() {
        return grid;
    }

    public boolean takeTurn(Scanner s, Player opponentPlayer) {
        // Returns true if the player has won the game after taking the turn;
        // false otherwise since the game has to go on.

        // Save-game feature will be triggered if the input is '0'.

        // Uses the same scanner object from App.java

        int col = -1;
        int row = -1;

        boolean successfullDiscDrop = false;

        while (!successfullDiscDrop) {

            try {

                // Trying to get the right input type for column - integer.
                // Save-game feature will be triggered if the input is '0'.
                System.out
                        .print(String.format(
                                "%s (%c) - Insert Disc - Enter the column number 1-7 (or enter '0' to save and exit): ",
                                this.name,
                                this.symbol));
                col = s.nextInt() - 1; // indexing it to 0 to help in array ops.
                s.nextLine();

                // If the input column is given as '0' (col = -1), the game will save and exit.
                if (col == -1) {
                    // 'this' is passed as player1 in the method as it is the current turn of 'this'
                    // player.
                    // While loading the game:
                    // 'this' player will play the first turn, due to the above reason.
                    // 'opponentPlayer' will play the second turn.
                    SaveGame saveGame = new SaveGame(this.getGrid(), this, opponentPlayer);

                    saveGame.saveThisGame();
                    System.out.println("Game saved successfully!");
                    System.exit(0);
                }

                if (col >= 0 && col <= 6) {
                    // If the entered column is valid, disc drop will be attempted.

                    // successfullDiscDrop is true when the dropDisc() returns a valid row.
                    // Unsuccessful drop disc is when dropDisc() returns -1 (ref. Grid.java).
                    row = this.grid.dropDisc(this.symbol, col);
                    successfullDiscDrop = (row != -1) ? true : false;

                    if (!successfullDiscDrop) {
                        // If disc drop is unsuccessful (column is full)
                        System.out.println("- - - Column is full. Try again.\n");
                    } else {
                        System.out.println(String.format("Disc (%c) placed at column: %d, row: %d", this.symbol,
                                col + 1, row + 1));
                    }
                } else {
                    // When the input is not an integer value.
                    System.out.println("- - - Invalid column number. Try again.\n");
                }

            } catch (InputMismatchException e) {
                System.out.println("- - - Invalid input. Please enter a valid integer.\n");
                s.nextLine();
            }

        }

        // After successful disc drop, checking if the player won the game.
        boolean isWinner = this.grid.checkWin(symbol, row, col);

        // Returning if the player won the game.
        return isWinner;

    }

}
