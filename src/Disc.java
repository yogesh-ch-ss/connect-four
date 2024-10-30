public class Disc {
    private char symbol;
    private int row;
    private int col;

    public Disc(char symbol, int col) {
        this.symbol = symbol;
        this.col = col;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return this.col;
    }
}
