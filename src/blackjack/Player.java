package blackjack;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Runner15
 */
public class Player {
    private int points;
    private String name;
    private int bet;
    private ArrayList<Card> hand;
    private Card newCard;
    private int handTot;
    private int handInt;
    private String playDec;
    private boolean isOut;
    private int checkBet;
    private int aceCount;
    
    
    public Player(String inName)
    {
        name = inName;
        points = 500;
        hand = new ArrayList();
        isOut = false;
    }
    public void Bet(int betIn) //Set bet for round
    {
        bet=betIn;
    }
    public void addCards(Card cardIn) //Add cards to hand
    {
        newCard = cardIn;
        hand.add(newCard);
    }
    public void hand() //Prints out all cards in hand
    {
        handInt = hand.size();
        for(int i=0; i<handInt; i++)
        {   
            System.out.print(hand.get(i).getBoth()+" ");
        }
        System.out.println();
    }
    public void card(int cardNum) //Prints out single card
    {
        System.out.println(hand.get(cardNum).getBoth());
    }
    public int handTot() //Finds total of hand
    {
        handTot = 0;
        aceCount = 0;
        for(int i=0; i<hand.size(); i++)
        {
            handTot += hand.get(i).getValue();
            if("A".equals(hand.get(i).getNum())) //Checks if there is Ace in hand
            {
                aceCount++;
            }
        }
        if(aceCount > 0 && handTot > 31) //If bust, but also Ace, use 1 for Ace
        {
            handTot -= 10;
        }
        return handTot;
    }
    public boolean play() //Hit or Stand
    {
        playDec = null;
        while(!"H".equals(playDec) && !"S".equals(playDec))
        {
            System.out.print("Do you want to (H)it or (S)tand? ");
            Scanner in = new Scanner(System.in);
            playDec = in.nextLine();
        }
        return "H".equals(playDec);
    }   
    public void setBust() //handTot > 31
    {
        isOut = true; //Sets variable to true
    }
    public void setBet() //Bet has to be between 1 and current points
    {
        checkBet = 99999;
        while(checkBet > points || checkBet <= 0)
        {
            System.out.println("How much do you want to bet?");
            System.out.print("You have "+points+" points: ");
            Scanner in = new Scanner(System.in);
            checkBet = in.nextInt();
        }
        bet = checkBet;
    }
    public boolean checkBust() //Returns true if busst
    {
        return isOut;
    }

    public void win(int i) { //If win, add bet to points
        points += bet;
        System.out.println(getName()+" won "+bet+" points! Current:  "+points);
    }

    public boolean lose(int i) { //If loss, subtract bet from points
        points -= bet;
        System.out.println(getName()+" lost "+bet+" points! Current: "+points);
        return points==0;
    }
    public void push(int i) { //If tie, keep current points
        points = points;
        System.out.println(getName()+" Tied! Current: "+points);
    }
    public String getName() //Gets player name
    {
        return name;
    }
    public void clearHand() //Clears hand after round
    {
        hand.clear();
    }
}