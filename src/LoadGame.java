import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame {

    public Object[] LoadSavedGame() {
        // Loads the game and returns the player1, player2, and grid object
        // to main game logic.
        try {
            File loadFile = new File("src/savefile.txt");

            Scanner s = new Scanner(loadFile);

            ArrayList<String> linesList = new ArrayList<>();
            while (s.hasNextLine()) {
                linesList.add(s.nextLine());
            }

            String[] linesArray = linesList.toArray(new String[0]);

            // Reading the player infos.
            String p1Name = linesArray[0];
            char p1Symbol = linesArray[1].charAt(0);
            String p2Name = linesArray[2];
            char p2Symbol = linesArray[3].charAt(0);

            Grid grid = new Grid();

            // Reading the grid from saved game file from bottom to top,
            // and inserting the discs on Grid object.
            // => capitalizing on discDrop() method to insert discs bottom-up.
            for (int i = linesArray.length - 1; i > 3; i--) {
                for (int j = 0; j < 7; j++) {
                    char discSymbol = linesArray[i].charAt(j);
                    grid.dropDisc(discSymbol, j);

                }
            }

            // Creating the Player objects using the collected info.
            Player player1 = new Player(p1Name, p1Symbol, grid);
            Player player2 = new Player(p2Name, p2Symbol, grid);

            s.close();

            // Returning the player1, player2, and the grid objects.
            return new Object[] { player1, player2, grid };

        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
        }

        // Incase there is no saved game, returning an empty object.
        return new Object[0];
    }

}
