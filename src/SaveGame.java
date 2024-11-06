import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGame {
    private Grid grid;
    private Player player1;
    private Player player2;

    public SaveGame(Grid grid, Player player1, Player player2) {
        this.grid = grid;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void createSaveGameFile() {
        try {
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
            this.createSaveGameFile();
            FileWriter savefile = new FileWriter("src/savefile.txt");

            savefile.write(player1.getName() + "\n");
            savefile.write(player1.getSymbol() + "\n");
            savefile.write(player2.getName() + "\n");
            savefile.write(player2.getSymbol() + "\n");

            savefile.close();

            System.out.println("Save file write successful!");
        } catch (IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }

    }

}
