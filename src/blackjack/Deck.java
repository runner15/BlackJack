package blackjack;
import java.util.ArrayList;
import java.util.*;
import java.util.Arrays;
/**
 * @author Runner15
 */
public class Deck {
    private ArrayList<Card> deck;
    private Card dealCard;
    private static int curCard;
    public Deck()
    {
        curCard = 0;
        deck = new ArrayList();
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<13; j++)
            {
                deck.add(new Card(i,j));
            }
        }
    }
    public void shuffle()
    {
        Collections.shuffle(deck);
        //System.out.println(deck);
    }
    public Card card(int num)
    {
        return deck.get(num);
    }
    public int countCards()
    {
        return deck.size();
    }
    public Card deal()
    {
        dealCard = deck.get(curCard);
        curCard++;
        return dealCard;
    }        
            
            
    public String getNum(int num)
    {
        return deck.get(num).getNum();
    }
    public String getSuit(int num)
    {
        return deck.get(num).getSuit();
    }
    public String getBoth(int num)
    {
        return deck.get(num).getBoth();
    }
}