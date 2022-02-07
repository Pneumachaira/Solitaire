import java.util.ArrayList;

public class Foundation{

    private ArrayList<Card> stack = new ArrayList<Card>();
    private String suit;

    public Foundation(String suit){
        this.suit = suit;
    }

    public String getSuit(){
        return suit;
    }

    public boolean addCheck(Card card){
        if (stack.size() == 0 && card.getRank() == 1){
            return true;
        }
        else if (stack.size()==0 && card.getRank() != 1){
            return false;
        }
        else if(stack.get(stack.size()-1).getRank() + 1 == card.getRank()){
            return true;
        }
        return false;
    }

    public void addCard(Card card){
        stack.add(card);
    }

    public boolean isEmpty(){
        if (stack.size() == 0){
            return true;
        }
        return false;
    }

    public String topCard(){
        return stack.get(stack.size()-1).getCard();
    }

    public int getSize(){
        return stack.size();
    }

}