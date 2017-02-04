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
    private Card card;
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
    public boolean getPlayers()
    {
        return player.size()!=0;
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
            System.out.println("It is "+player.get(i).getName()+"'s turn.");
            for(int j=0; j<player.size();j++)
            {
                System.out.print(player.get(i).getName()+"'s cards: ");
                player.get(j).hand();
            }
            System.out.print("Dealer's cards: ** ** "); dealer.card(2);
            System.out.println(player.get(i).getName()+"'s total = "+player.get(i).handTot());
            if(player.get(i).play())
            {
                card = deck.deal();
                System.out.println(card.getBoth());
                player.get(i).addCards(card);
                if(player.get(i).handTot() <= 31)
                {
                    i--;
                }
                else
                {
                    System.out.println(player.get(i).getName()+" Busted");
                    player.get(i).setBust();
                    if(player.get(i).lose(i))
                    {
                        player.remove(i);
                    }
                }
            }
        }
    }
    public void dealer()
    {
        System.out.print("Dealer's hand: ");
        dealer.hand();
        System.out.println("Dealer's total: "+dealer.handTot());
        while(dealer.handTot() <= 26)
        {
            dealer.addCards(deck.deal());
            System.out.print("Dealer's hand: ");
            dealer.hand();
            System.out.println("Dealer's total: "+dealer.handTot());
        }
    }
    public void checkWin()
    {
        for(int i = 0; i < player.size(); i++)
        {
            if(!player.get(i).checkBust() && (player.get(i).handTot()>dealer.handTot()) && dealer.handTot()>32)
            {
                player.get(i).win(i);
            }
            else if(player.get(i).handTot()==dealer.handTot())
            {
                player.get(i).push(i);
            }
            else
            {
                if(player.get(i).lose(i))
                {
                    player.remove(i);
                    System.out.println(player.get(i).getName()+" is out of the game");
                }
            }
        }
    }
}