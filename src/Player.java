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
        return "Player: " + this.name + "(" + this.symbol + ")";
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

    public boolean takeTurn(Scanner s) {
        // Returns true if the turn is successfull (disc is inserted successfully);
        // false otherwise
        // Uses the same acanner object from App.java

        int col = -1;
        boolean successfullDiscDrop = false;

        while (!successfullDiscDrop) {
            System.out
                    .print(String.format("%s (%c) - Insert Disc - Enter the column number: ", this.name, this.symbol));
            col = s.nextInt();
            s.nextLine();

            if (col >= 0 && col <= 6) {
                // If the entered column is valid, disc drop will be attempted.
                successfullDiscDrop = this.grid.dropDisc(this.symbol, col);

                if (!successfullDiscDrop) {
                    // If disc drop is unsuccessful (column is full => ref. Grid.java)
                    System.out.println("- - - Column is full. Try again.");
                }
            } else {
                System.out.println("- - - Invalid column number. Try again.");
            }
        }

        // Returning True after the turn is over once disc drop is successful.
        // The turn cannot be unsuccessful because takeTurn() will only be called
        // when the grid is not empty => a disc can still be dropped.
        // This return condition is kept for future improvement, and is not necessary.
        return true;

    }

}
