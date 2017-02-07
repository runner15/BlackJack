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
    public Player dealer;
    private Card card;
    public Game()
    {
        player = new ArrayList();
        while(numPlay<1 || numPlay>4) //1 to 4 players
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
    public boolean getPlayers() //Makes sure there are players
    {
        return !player.isEmpty();  
    }
    public Deck deck()
    {
        return deck;
    }
    public void deal() //Gives 3 cards to each player+dealer
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
    public void bet() //Lets players set point bets
    {
        for(int i = 0; i < player.size(); i++)
        {
            player.get(i).setBet();
        }        
    }
    public void turn() //Asks players to hit or stand
    {
        for(int i = 0; i < player.size(); i++)
        {
            System.out.println("It is "+player.get(i).getName()+"'s turn.");
            for(int j=0; j<player.size();j++)
            {
                System.out.print(player.get(j).getName()+"'s cards: ");
                player.get(j).hand();
            }
            System.out.print("Dealer's cards: ** ** "); dealer.card(2);
            System.out.println(player.get(i).getName()+"'s total = "+player.get(i).handTot());
            if(player.get(i).play()) //If player picks Hit
            {
                card = deck.deal(); //Get 1 card
                System.out.println(card.getBoth());
                player.get(i).addCards(card); //Gives 1 card to players hand
                if(player.get(i).handTot() <= 31)
                {
                    i--; //If player is less than 31, ask them if they want to hit again
                }
                else
                {
                    System.out.println(player.get(i).getName()+" Busted");
                    player.get(i).setBust();
                    if(player.get(i).lose())
                    {
                        player.remove(i); //If player has 0 points, remove them from game
                        i--;
                    }
                }
            }
        }
    }
    public void dealer() //Gives dealer cards until hand>26
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
    public void checkWin() //Checks each player vs the dealer
    {
        for(int i = 0; i < player.size(); i++)
        {
            boolean playerWin = ((player.get(i).handTot()>dealer.handTot()) || (dealer.handTot()>=32));
            if((!player.get(i).checkBust()) && playerWin) //Win if player doesn't bust, higher than dealer, or dealer busts
            {
                player.get(i).win();
            }
            else if(player.get(i).handTot()==dealer.handTot()) //Ties push
            {
                player.get(i).push();
            }
            else
            {
                if(player.get(i).lose()) //Check if player has 0 points
                {
                    System.out.println(player.get(i).getName()+" is out of the game");
                    player.remove(i);
                    i--;
                    break;
                }
            }
            player.get(i).clearHand(); //Clear hand for next round
        }
    }
}