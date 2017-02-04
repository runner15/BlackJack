package blackjack;
/**
 * @author Runner15
 */
public class Driver {
    
    public static void main(String[] args)
    {
        Game game = new Game();
        game.deal();
        game.bet();
        game.turn();
        game.dealer();
        game.checkWin();
    }
}
