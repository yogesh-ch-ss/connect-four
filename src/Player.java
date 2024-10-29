public class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String toString() {
        return "Player: " + this.name + "(" + this.symbol + ")";
    }

}
