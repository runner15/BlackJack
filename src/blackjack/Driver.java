package blackjack;
import java.util.Scanner;
/**
 * @author Runner15
 */
public class Driver {
    
    public static void main(String[] args)
    {
        Game game = new Game();
        String newRound = null;
        while(game.getPlayers() && !"S".equals(newRound))
        {
            game.deck.shuffle();
            game.deal();
            game.bet();
            game.turn();
            if(game.getPlayers())
            {
                game.dealer();
            }
            game.checkWin();
            game.dealer.clearHand();
            System.out.print("Do you want to (S)top? ENTER to continue ");
            Scanner in = new Scanner(System.in);
            newRound = in.nextLine();
        }
        System.out.println("Game Over");
    }
}
