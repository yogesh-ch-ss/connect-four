import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

// How the save game feature works?
// 1. SaveGame object gets the grid, player1, player2 objects.
// 2. Checks if the game file exists by createSaveGameFile().
//      - If it doesn't exist -> create file.
//      - If it exists -> rewrites.
// 3. Storing the game info in the following parsed format:
/*  
    * Player 1 name
    * Player 1 symbol
    * Player 2 name
    * Player 2 symbol
    * 6x7 game grid.
*/

public class SaveGame {
    private Grid grid;
    private Player player1;
    private Player player2;

    public SaveGame(Grid grid, Player player1, Player player2) {
        // Gets the grid and player infos to save.
        this.grid = grid;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void createSaveGameFile() {
        // A method just to create a new file / check if the file exists.
        try {
            // Creating a new save game file.
            File newFile = new File("src/savefile.txt");
            if (newFile.createNewFile()) {
                System.out.println("New save file created.");
            } else {
                System.out.println("Save file already exists. Re-writing...");
            }

        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

    }

    public void saveThisGame() {

        try {
            // Creating / checking the save game file.
            this.createSaveGameFile();

            FileWriter savefile = new FileWriter("src/savefile.txt");

            // Storing the game info in the following format:
            /*
             * Player 1 name
             * Player 1 symbol
             * Player 2 name
             * Player 2 symbol
             * 6x7 game grid.
             */

            savefile.write(player1.getName() + "\n");
            savefile.write(player1.getSymbol() + "\n");
            savefile.write(player2.getName() + "\n");
            savefile.write(player2.getSymbol() + "\n");

            for (int i = 0; i < this.grid.getGridRowLength(); i++) {
                for (int j = 0; j < this.grid.getGridColumnLength(); j++) {
                    char symbol = this.grid.getDiscSymbolAt(i, j);
                    savefile.write(symbol + "");
                }
                savefile.write("\n");
            }

            savefile.close();
            System.out.println("Save file write successful!");

        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

    }

}
