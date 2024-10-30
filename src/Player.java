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

}
