public class Grid {
    private char gameGrid[][] = new char[6][7];

    public Grid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameGrid[i][j] = '.';
            }
        }
    }

    public String toString() {
        // returning the 2D game grid

        String output = "\n";
        for (int j = 0; j < 7; j++) {
            output += " " + (j + 1);
        }
        output += "\n";

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                output += " " + this.gameGrid[i][j];
            }
            output += "\n";
        }
        output += "\n";

        return output;

    }


    public void dropDisc(Disc discObj) {
        
    }

}
