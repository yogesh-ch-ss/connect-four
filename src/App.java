import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("\n\nCONNECT FOUR\n");

        // Getting the player info

        Scanner s = new Scanner(System.in);

        System.out.print("Player 1 - Enter your Name >>> ");
        String p1Name = s.nextLine();

        char p1Symbol = 0;
        do {
            System.out.print("Player 1 - Pick your Symbol -> X / O >>> ");
            p1Symbol = s.nextLine().charAt(0);
        } while (p1Symbol != 'x' && p1Symbol != 'o' && p1Symbol != 'X' && p1Symbol != 'O');

        p1Symbol = Character.toUpperCase(p1Symbol);

        System.out.print("Player 2 - Enter your Name >>> ");
        String p2Name = s.nextLine();

        char p2Symbol = 0;
        if (p1Symbol == 'x' || p1Symbol == 'X') {
            p2Symbol = 'O';
        } else {
            p2Symbol = 'X';

        }
        System.out.println("Player 2 - Your Symbol is >>> " + p2Symbol);

        System.out.println(
                String.format("\n - %-15s %-15s\n > %-15s %c\n > %-15s %c",
                        "PLAYER", "SYMBOL", p1Name, p1Symbol, p2Name, p2Symbol));

        s.close();

        // Initialising the game grid
        Grid grid = new Grid();

        // Creating player objects
        Player player1 = new Player(p1Name, p1Symbol, grid);
        Player player2 = new Player(p2Name, p2Symbol, grid);

        // Both the players play on the same grid "grid".
        // Thus, the same grid object is referenced.
        // Updating player1's grid will reflect in player2's grid, and vice-versa, as
        // they both are the essentially same referenced grid.

        System.out.println(grid);

    }

    public static void takeTurns(Player player1, Player player2) {

    }
}
