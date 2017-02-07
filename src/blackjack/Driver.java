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
        while(game.getPlayers() && !"S".equals(newRound)) //If there are players left and use did not quit, play game
        {
            game.deck.shuffle(); //Shuffle the deck before each round
            game.deal();
            game.bet(); //Gets bets from players
            game.turn(); //Goes through players turns
            if(game.getPlayers()) //Checks if any players have points
            {
                game.dealer(); //Gets cards until hand>26
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
