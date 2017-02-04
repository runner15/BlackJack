package blackjack;
/**
 * @author Runner15
 */
public class Card {
    private static String[] nums = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private static String[] suits = {"Clubs","Diamonds","Hearts","Spades"};
    private static String[] suitsCode = {"♠","♦","♥","♣"};
    private int value;

    public int suit, num, code;
    
    public Card(int inSuit, int inNum)
    {
        suit = inSuit;
        code = inSuit;
        num  = inNum;
    }    
    public String getNum()
    {
        return nums[num];
    }
    public String getSuit()
    {
        return suits[suit];
    }
    public String getBoth()
    {
        return nums[num]+suitsCode[code];
    }
    public int getValue()
    {
        value = 0;
        if(num>10)
        {
            value = 10;
        }
        else if(num==0)
        {
            value = 11;
        }
        else
        {
            value = num+1;
        }
        return value;
    }
}
