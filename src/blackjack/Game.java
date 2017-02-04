package blackjack;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Runner15
 */
public class Game {
    private int numPlay = 0;
    private ArrayList<Player> player;
    public Deck deck;
    private Player dealer;
    public Game()
    {
        player = new ArrayList();
        while(numPlay<1 || numPlay>4)
        {
            System.out.print("How many players? (Between 1 and 4) ");
            Scanner in = new Scanner(System.in);
            numPlay = in.nextInt();
        }
        deck = new Deck();
        deck.shuffle();
        dealer = new Player("Dealer");
        for(int i = 0; i < numPlay; i++)
        {
            player.add(new Player("Player"+i));
        }
    }
    public ArrayList<Player> getPlayers()
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
            for(int i=0; i < player.size(); i++)
            {
                player.get(i).addCards(deck.deal());
            }
        }
    }
    public void bet()
    {
        for(int i = 0; i < player.size(); i++)
        {
            player.get(i).setBet();
        }        
    }
    public void turn()
    {
        for(int i = 0; i < player.size(); i++)
        {
            System.out.println("It is Player "+(i+1)+"'s turn.");
            for(int j=0; j<player.size();j++)
            {
                System.out.print("Player "+(j+1)+"'s cards: ");
                player.get(i).hand();
            }
            System.out.print("Dealer's cards: ** ** "); dealer.card(2);
            System.out.println("Player "+(i+1)+"'s total = "+player.get(i).handTot());
            if(player.get(i).play())
            {
                player.get(i).addCards(deck.deal());
                if(player.get(i).handTot() <= 31)
                {
                    i--;
                }
                else
                {
                    System.out.println("Player "+(i+1)+" Busted");
                    player.get(i).setBust();
                }
            }
        }
    }
    public void dealer()
    {
        
    }
    public void checkWin()
    {
        for(int i = 0; i < player.size(); i++)
        {
            if(!player.get(i).checkBust() && (player.get(i).handTot() > dealer.handTot()))
            {
                player.get(i).win();
            }
            else
            {
                player.get(i).lose();
            }
        }
    }
}