public class Grid {
    private Disc grid[][] = new Disc[6][7];

    public Grid() {
        // Initialize an empty 6x7 grid

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // Store empty discs => symbol '.'
                grid[i][j] = new Disc('.', i, j);
            }
        }
    }

    public String toString() {
        // Returning the 2D game grid with the column numbers

        String output = "\n|";
        for (int j = 0; j < 7; j++) {
            output += String.format(" %d |", j);
        }

        output += "\n|";
        for (int j = 0; j < 7; j++) {
            output += "-+-|";
        }
        output += "\n";

        for (int i = 0; i < 6; i++) {
            output += "|";
            for (int j = 0; j < 7; j++) {
                output += String.format(" %c |", this.grid[i][j].getSymbol());
            }
            output += "\n";
        }
        output += "\n";

        return output;

    }

    public boolean dropDisc(char symbol, int col) {
        // Returns true if the disc is inserted successfully; false otherwise

        for (int row = this.grid.length - 1; row >= 0; row--) {
            if (this.grid[row][col].getSymbol() == '.') {
                // Create a disc object with the given symbol and the column,
                // and the row obtained from this method
                Disc disc = new Disc(symbol, row, col);
                this.grid[row][col] = disc;
                return true;
            }
        }
        // When the given column is full, the disc cannot be dropped => unsuccessful
        return false;

    }

    public boolean isFull() {
        // Checks if the top row is fully filled
        for (int j = 0; j < 7; j++) {
            if (this.grid[0][j].getSymbol() == '.') {
                return false;
            }
        }
        return true;
    }

    public void checkWin() {

    }

}
