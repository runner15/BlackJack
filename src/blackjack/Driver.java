package blackjack;
import java.util.Scanner;
/**
 * @author Runner15
 */
public class Driver {
    
    public static void main(String[] args)
    {
        Game game = new Game();
        while(game.getPlayers())
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
        }
        System.out.println("Game Over");
    }
}
