public class Grid {
    private Disc grid[][] = new Disc[6][7];

    public Grid() {
        // Initialize an 6x7 grid with empty '.' discs.

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                // Store empty discs => symbol '.'
                grid[i][j] = new Disc('.', i, j);
            }
        }
    }

    @Override
    public String toString() {
        // Returning the 2D game grid with the column numbers.

        String output = "\n * |";
        for (int j = 0; j < this.getGridColumnLength(); j++) {
            output += String.format(" %d |", j + 1);
        }

        output += "\n---|";
        for (int j = 0; j < this.getGridColumnLength(); j++) {
            output += "-+-|";
        }
        output += "\n";

        for (int i = 0; i < this.getGridRowLength(); i++) {
            output += String.format(" %d |", i + 1);
            for (int j = 0; j < this.getGridColumnLength(); j++) {
                output += String.format(" %s |", this.grid[i][j]);
            }
            output += "\n";
        }
        output += "\n";

        return output;

    }

    public int getGridRowLength() {
        // Returns the no. of rows.
        return this.grid.length;
    }

    public int getGridColumnLength() {
        // Returns the no. of columns.
        return this.grid[0].length;
    }

    public char getDiscSymbolAt(int i, int j) {
        return this.grid[i][j].getSymbol();
    }

    public int dropDisc(char symbol, int col) {
        // Returns the inserted row if the disc is inserted successfully; -1 otherwise.

        for (int row = this.getGridRowLength() - 1; row >= 0; row--) {
            if (this.grid[row][col].getSymbol() == '.') {
                // Create a disc object with the given symbol and the column,
                // and the row obtained from this method.
                Disc disc = new Disc(symbol, row, col);
                this.grid[row][col] = disc;
                return row;
            }
        }
        // When the given column is full, the disc cannot be dropped => row = -1.
        return -1;

    }

    public boolean isFull() {
        // Checks if the top row is fully filled.
        for (int j = 0; j < this.getGridColumnLength(); j++) {
            if (this.grid[0][j].getSymbol() == '.') {
                return false;
            }
        }
        return true;
    }

    public boolean checkWin(char symbol, int row, int col) {
        // Checks if four discs are equal in row / column / diagonal.

        return this.checkColWin(symbol, row, col) || this.checkRowWin(symbol, row, col)
                || this.checkDiagonalWinTLBR(symbol, row, col) || this.checkDiagonalWinBLTR(symbol, row, col);
    }

    public boolean checkColWin(char symbol, int row, int col) {
        // Time complexity = O(1) - constant.
        // Checks if 4 consecutive rows below the inserted disc is the same.

        for (int i = row; i < row + 4; i++) {
            // If the row exceedes the final row (6) OR if the symbols don't match,
            // the player hasn't won yet.
            if (i == this.getGridRowLength() || this.grid[i][col].getSymbol() != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkRowWin(char symbol, int row, int col) {
        // Time complexity = O(1) - constant.
        // Checks if 4 consecutive columns on the left and right of the inserted disc is
        // the same.

        int count = 1; // counts the consecutive symbols.
        // The inserted symbol is included in the count. Thus, count = 1.

        // Left columns
        for (int j = col - 1; j > col - 4; j--) {
            // If the column exceedes the first row (-1) OR if the symbols don't match,
            // count stops counting.
            if (j == -1 || this.grid[row][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a row.
            if (count == 4)
                return true;
        }

        // Right columns
        for (int j = col + 1; j < col + 4; j++) {
            // If the row exceedes the final column (7) OR if the symbols don't match,
            // count stops counting.
            if (j == 7 || this.grid[row][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a row.
            if (count == 4)
                return true;
        }

        // Returns false since 4 symbols are not yet continous in a row.
        return false;
    }

    public Boolean checkDiagonalWinTLBR(char symbol, int row, int col) {
        // Time complexity = O(1) - constant.
        // Checks if the Top-Left-Bottom-Right '\' diagonal symbols are the same.
        int count = 1;

        int i = row - 1;
        int j = col - 1;

        // Top-Left diagonal - reduce row, reduce column.
        while (i > -1 && j > -1) {
            // If row exceeds the first row (-1),
            // OR column exceeds the first column (-1),
            // OR if the symbold don't match, count stops counting.
            // This while will run only when both the row AND the column are in the range.
            if (this.grid[i][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a diagonal.
            if (count == 4)
                return true;

            i -= 1;
            j -= 1;
        }

        i = row + 1;
        j = col + 1;

        // Bottom-Right diagonal - increase row, increase column.
        while (i < this.getGridRowLength() && j < this.getGridColumnLength()) {
            // If row exceeds the last row (6),
            // OR column exceeds the last column (7),
            // OR if the symbold don't match, count stops counting.
            // This while will run only when both the row AND the column are in the range.
            if (this.grid[i][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a diagonal.
            if (count == 4)
                return true;

            i += 1;
            j += 1;
        }

        // Returns false since 4 symbols are not yet continous in a diagonal.
        return false;
    }

    public Boolean checkDiagonalWinBLTR(char symbol, int row, int col) {
        // Time complexity = O(1) - constant.
        // Checks if the Bottom-Left-Top-Right '/' diagonal symbols are the same.
        int count = 1;

        int i = row + 1;
        int j = col - 1;

        // Bottom-Left diagonal - increase row, reduce column.
        while (i < this.getGridRowLength() && j > -1) {
            // If row exceeds the last row (6),
            // OR column exceeds the first column (-1),
            // OR if the symbold don't match, count stops counting.
            // This while will run only when both the row AND the column are in the range.
            if (this.grid[i][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a diagonal.
            if (count == 4)
                return true;

            i += 1;
            j -= 1;
        }

        i = row - 1;
        j = col + 1;

        // Top-Right diagonal - reduce row, increase column.
        while (i > -1 && j < this.getGridColumnLength()) {
            // If row exceeds the last row (6),
            // OR column exceeds the last column (7),
            // OR if the symbold don't match, count stops counting.
            // This while will run only when both the row AND the column are in the range.
            if (this.grid[i][j].getSymbol() != symbol) {
                break;
            }

            count += 1;

            // Returns true when 4 symbols are continous in a diagonal.
            if (count == 4)
                return true;

            i -= 1;
            j += 1;
        }

        // Returns false since 4 symbols are not yet continous in a diagonal.
        return false;
    }

}
