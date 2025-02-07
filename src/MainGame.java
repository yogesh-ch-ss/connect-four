import java.util.Scanner;

public class MainGame {


    public void createPlayers() {

        Scanner s = new Scanner(System.in);

        System.out.print("Player 1 - Enter your Name >>> ");
        String p1Name = s.nextLine();

        char p1Symbol = 0;
        do {
            System.out.print(p1Name + " - Pick your Symbol -> X / O (Enter a valid choice) >>> ");
            p1Symbol = s.nextLine().charAt(0);
        } while (p1Symbol != 'x' && p1Symbol != 'o' && p1Symbol != 'X' && p1Symbol != 'O');

        p1Symbol = Character.toUpperCase(p1Symbol);

        System.out.print("\nPlayer 2 - Enter your Name >>> ");
        String p2Name = s.nextLine();

        char p2Symbol = 0;
        if (p1Symbol == 'x' || p1Symbol == 'X') {
            p2Symbol = 'O';
        } else {
            p2Symbol = 'X';

        }
        System.out.println(p2Name + " - Your Symbol is >>> " + p2Symbol);

        System.out.println(
                String.format("\n > %-15s >>>   %-15s\n > %-15s >>>   %c\n > %-15s >>>   %c\n\n Enjoy the game! \n\n",
                        "PLAYER", "SYMBOL", p1Name, p1Symbol, p2Name, p2Symbol));

        s.close();
    }

}
