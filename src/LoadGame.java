import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGame {

    public Object[] LoadSavedGame() {
        try {
            File loadFile = new File("src/savefile.txt");

            Scanner s = new Scanner(loadFile);

            ArrayList<String> linesList = new ArrayList<>();
            while (s.hasNextLine()) {
                linesList.add(s.nextLine());
            }

            String[] linesArray = linesList.toArray(new String[0]);

            String p1Name = linesArray[0];
            char p1Symbol = linesArray[1].charAt(0);
            String p2Name = linesArray[2];
            char p2Symbol = linesArray[3].charAt(0);

            Grid grid = new Grid();

            for (int i = linesArray.length - 1; i > 3; i--) {
                for (int j = 0; j < 7; j++) {
                    char discSymbol = linesArray[i].charAt(j);
                    grid.dropDisc(discSymbol, j);

                }
            }

            Player player1 = new Player(p1Name, p1Symbol, grid);
            Player player2 = new Player(p2Name, p2Symbol, grid);

            s.close();

            return new Object[] { player1, player2, grid };

        } catch (FileNotFoundException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
        return new Object[0];
    }

}
