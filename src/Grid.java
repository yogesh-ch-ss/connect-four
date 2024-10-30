public class Grid {
    private Disc grid[][] = new Disc[6][7];

    public Grid() {
        // Initialize an empty grid
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grid[i][j] = new Disc('.', j);
                grid[i][j].setRow(i);
            }
        }
    }

    public String toString() {
        // Returning the 2D game grid with the column numbers

        String output = "\n";
        for (int j = 0; j < 7; j++) {
            output += String.format(" %d", j + 1);

        }
        output += "\n";

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                output += String.format(" %c", this.grid[i][j].getSymbol());
            }
            output += "\n";
        }
        output += "\n";

        return output;

    }

    public boolean dropDisc(Disc disc) {

        for (int i = this.grid.length - 1; i >= 0; i--) {
            if (this.grid[i][disc.getCol()].getSymbol() == '.') {
                this.grid[i][disc.getCol()] = disc;
                disc.setRow(i);
                return true;
            }
        }
        return false;

    }

    public boolean isFull() {
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
