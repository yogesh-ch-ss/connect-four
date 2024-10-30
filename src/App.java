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
        System.out.println("Player2 - Your Symbol is >>> " + p2Symbol);

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

        takeTurns(player1, player2);

        System.out.println(grid);

    }

    public static void takeTurns(Player player1, Player player2) {

        Player currentPlayer = player1;

        Scanner s1 = new Scanner(System.in);

        {
            System.out.print(
                    String.format("%s (%c) insert disc. Enter the column number: ",
                            currentPlayer.getName(), currentPlayer.getSymbol()));

            int col = -1;

            if (s1.hasNextInt()) {
                col = s1.nextInt() - 1; // Adjust for zero-based indexing
                if (col < 0 || col >= 7) {
                    System.out.println("Invalid column number. Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                s1.next(); // Clear the invalid input
            }

            // int col = s.nextInt() - 1;
            // s.nextLine();

            Disc disc = new Disc(currentPlayer.getSymbol(), col);
            currentPlayer.getGrid().dropDisc(disc);
            System.out.println(currentPlayer.getGrid());

        }

        currentPlayer = player2;
        {
            System.out.print(
                    String.format("%s (%c) insert disc. Enter the column number: ",
                            currentPlayer.getName(), currentPlayer.getSymbol()));

            int col = 5;
            // int col = s.nextInt() - 1;
            // s.nextLine();

            Disc disc = new Disc(currentPlayer.getSymbol(), col);
            currentPlayer.getGrid().dropDisc(disc);
            System.out.println(currentPlayer.getGrid());

        }

        s1.close();

    }
}
