import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("\n\nCONNECT FOUR\n");

        // Getting the player info

        Scanner s = new Scanner(System.in);

        int startGame = 0;
        do {
            System.out.print("Select game: \n 1. New Game \n 2. Load Saved Game \n\nEnter 1 or 2 >>>");
            startGame = s.nextInt();
            s.nextLine();
        } while (startGame != 1 && startGame != 2);

        if (startGame == 1) {
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

            // Initialising the game grid
            Grid grid = new Grid();
            System.out.println(grid);

            // Creating player objects
            Player player1 = new Player(p1Name, p1Symbol, grid);
            Player player2 = new Player(p2Name, p2Symbol, grid);

            // Both the players play on the same grid "grid".
            // Thus, the same grid object is referenced.
            // Updating player1's grid will reflect in player2's grid, and vice-versa,
            // as they both are the essentially same referenced grid.

            playGame(player1, player2, s);

        } else {
            LoadGame loadGame = new LoadGame();
            // gameInfo holds the player1, player2, and the grid objects.
            // (ref. LoadGame.java)
            Object[] gameInfo = loadGame.LoadSavedGame();

            if (gameInfo.length < 1) {
                // Saved game does not exist when gameInfo is an empty object.
                System.out.println("No savegame exists. Goodbye!\n");
                System.exit(0);
            }

            // Creating the Player and Grid objects from gameInfo.
            Player player1 = (Player) gameInfo[0];
            Player player2 = (Player) gameInfo[1];
            Grid grid = (Grid) gameInfo[2];

            System.out.println(player1);
            System.out.println(player2);
            System.out.println(grid);

            playGame(player1, player2, s);

        }

        // Deleting the save game file when the game is over.
        File file = new File("src/savefile.txt");

        // Checking if the file exists and trying to delete it.
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("savegame file deleted successfully.");
            } else {
                System.out.println("Failed to delete the savegame file.");
            }
        } else {
            System.out.println("savegame file does not exist.");
        }

        System.out.println("\nThank you for playing connect-four.\n");
        System.out.println("<<< Developed by Yogesh Chandra Singh Samant >>>\n\n");

        s.close();

        System.exit(0);

    }

    public static void playGame(Player player1, Player player2, Scanner s) {
        // The game playing method which runs until either the grid gets full,
        // or when a player wins.

        // New branch test.

        Player currentplayer = player1;
        Player opponentPlayer = player2;
        boolean isWinner = false;

        // SaveGame test.
        // SaveGame saveGame = new SaveGame(currentplayer.getGrid(), player1, player2);
        // saveGame.saveThisGame();

        while (!currentplayer.getGrid().isFull() && !isWinner) {
            // Current player will take a turn and will check if the player won the game.
            // Feeding the opponentPlayer as well to enable the save game functionality.
            isWinner = currentplayer.takeTurn(s, opponentPlayer);
            System.out.println(currentplayer.getGrid());

            if (isWinner) {
                System.out.println(
                        String.format("%s (%c) won the game!", currentplayer.getName(), currentplayer.getSymbol()));
                break;
            }

            // Alternating current player to take turns.
            opponentPlayer = currentplayer;
            currentplayer = (currentplayer == player1) ? player2 : player1;

        }

        // System.out.println("Grid is full.\n");
        System.out.println("\nGAME OVER\n");

    }

}
