public class Disc {
    private char symbol;
    private int row;
    private int col;

    public Disc(char symbol, int row, int col) {
        this.symbol = symbol;
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "" + this.symbol;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}
