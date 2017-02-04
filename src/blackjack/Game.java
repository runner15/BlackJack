package blackjack;
import java.util.Scanner;
/**
 * @author Runner15
 */
public class Game {
    private int numPlay = 0;
    private Player[] player;
    public Deck deck;
    private Player dealer;
    public Game()
    {
        while(numPlay<1 || numPlay>4)
        {
            System.out.print("How many players? (Between 1 and 4) ");
            Scanner in = new Scanner(System.in);
            numPlay = in.nextInt();
        }
        deck = new Deck();
        deck.shuffle();
        player = new Player[numPlay];
        dealer = new Player("Dealer");
        for(int i = 0; i < numPlay; i++)
        {
            player[i] = new Player("Player"+i);
        }
    }
    public Player[] getPlayers()
    {
        return player;
    }
    public Deck deck()
    {
        return deck;
    }
    public void deal()
    {
        for(int j=0;j<3;j++)
        {
            dealer.addCards(deck.deal());
            for(int i=0; i < numPlay; i++)
            {
                player[i].addCards(deck.deal());
            }
        }
    }
    public void bet()
    {
        for(int i = 0; i < numPlay; i++)
        {
            player[i].setBet();
        }        
    }
    public void turn()
    {
        for(int i = 0; i < numPlay; i++)
        {
            System.out.println("It is Player "+(i+1)+"'s turn.");
            for(int j=0; j<numPlay;j++)
            {
                System.out.print("Player "+(j+1)+"'s cards: ");
                player[j].hand();
            }
            System.out.print("Dealer's cards: ** ** "); dealer.card(2);
            System.out.println("Player "+(i+1)+"'s total = "+player[i].handTot());
            if(player[i].play())
            {
                player[i].addCards(deck.deal());
                if(player[i].handTot() <= 31)
                {
                    i--;
                }
                else
                {
                    System.out.println("Player "+(i+1)+" Busted");
                    player[i].setBust();
                }
            }
        }
    }
    public void dealer()
    {
        
    }
    public void checkWin()
    {
        for(int i = 0; i < numPlay; i++)
        {
            if(!player[i].checkBust() && (player[i].handTot() > dealer.handTot()))
            {
                player[i].win();
            }
            else
            {
                player[i].lose();
            }
        }
    }
}