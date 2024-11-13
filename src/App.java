import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // The main initiates the player and grid objects and invokes playGame().

        System.out.println("\n\nCONNECT FOUR\n");

        Scanner s = new Scanner(System.in);

        int startGame = 0;
        do {
            // Getting the game info - New Game / Load Saved Game.
            try {
                System.out.print("Select game: \n 1. New Game \n 2. Load Saved Game \n\nEnter 1 or 2 >>> ");
                startGame = s.nextInt();
                s.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("- - - Invalid input. Please enter a valid integer.\n");
                s.nextLine();
            }
        } while (startGame != 1 && startGame != 2);

        if (startGame == 1) {
            // startGame = 1 => New Game
            // Create new Player and Grid objects.
            String p1Name = "";
            do {
                try {
                    System.out.print("Player 1 - Enter your Name >>> ");
                    p1Name = s.nextLine();

                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            } while (p1Name.length() < 1);

            char p1Symbol = 0;
            do {
                try {
                    System.out.print("Player 1 - Pick your Symbol -> X / O >>> ");
                    p1Symbol = s.nextLine().charAt(0);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Enter a valid symbol.");
                }
            } while (p1Symbol != 'x' && p1Symbol != 'o' && p1Symbol != 'X' && p1Symbol != 'O');

            p1Symbol = Character.toUpperCase(p1Symbol);

            String p2Name = "";
            do {
                try {
                    System.out.print("Player 2 - Enter your Name >>> ");
                    p2Name = s.nextLine();

                } catch (Exception e) {
                    System.out.println("Invalid Input");
                }
            } while (p2Name.length() < 1);

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
            // as they both are essentially the same referenced grid.

            // Start playing the game.
            playGame(player1, player2, s);

        } else {
            // startGame = 2 => Load Saved Game.
            // Obtain Player and Grid objects from the save game file.
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

            // Start playing the game.
            playGame(player1, player2, s);

        }

        // Deleting the save game file when the game is over.
        deleteSaveGameFile();

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

        // When the grid gets full, the game is tied.
        if (currentplayer.getGrid().isFull())
            System.out.println("Grid is full.\nGame Tied!");

        System.out.println("\nGAME OVER\n");

    }

    public static void deleteSaveGameFile() {
        // Deletes the save game file.

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
    }

}
