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

    public int dropDisc(char symbol, int col) {
        // Returns the inserted row if the disc is inserted successfully; -1 otherwise

        for (int row = this.grid.length - 1; row >= 0; row--) {
            if (this.grid[row][col].getSymbol() == '.') {
                // Create a disc object with the given symbol and the column,
                // and the row obtained from this method
                Disc disc = new Disc(symbol, row, col);
                this.grid[row][col] = disc;
                return row;
            }
        }
        // When the given column is full, the disc cannot be dropped => row = -1
        return -1;

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

    public boolean checkWin(char symbol, int row, int col) {
        return this.checkColWin(symbol, row, col) || this.checkRowWin(symbol, row, col);
    }

    public boolean checkRowWin(char symbol, int row, int col) {
        // Checks if 4 consecutive columns on the left and right of the inserted disc is
        // the same.

        int count = 1; // counts the consecutive symbols

        // Left columns
        for (int j = col - 1; j > col - 4; j--) {
            // If the column exceedes the first row (-1) OR if the symbols don't match,
            // the player hasn't won yet.
            if (j == -1 || this.grid[row][j].getSymbol() != symbol) {
                break;
            }

            count += 1;
        }

        // Right columns
        for (int j = col + 1; j < col + 4; j++) {
            // If the row exceedes the final column (7) OR if the symbols don't match,
            // the player hasn't won yet.
            if (j == 7 || this.grid[row][j].getSymbol() != symbol) {
                break;
            }

            count += 1;
        }

        System.out.println(count);
        return (count >= 4) ? true : false;
    }

    public boolean checkColWin(char symbol, int row, int col) {
        // Checks if 4 consecutive rows below the inserted disc is the same.
        for (int i = row; i < row + 4; i++) {
            // If the row exceedes the final row (6) OR if the symbols don't match,
            // the player hasn't won yet.
            if (i == 6 || this.grid[i][col].getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }

    public void checkDiagonalWin() {

    }

}
