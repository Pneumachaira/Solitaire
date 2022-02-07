import java.util.ArrayList;

public class Tableau{
    private ArrayList<Card> stack = new ArrayList<Card>();

    public void addCard(Card card){
        stack.add(card);
    }

    // public ArrayList<Card> grabMultiple(String input){
    //     // return subarray
    // }

    public String grabOne(int index){
        return stack.get(index).getCard();
    }

    public void showStack(){
        String showEm = "";
        if(stack.get(0).isFaceUp()){
            showEm = stack.get(0).getCard() +", ";
        }
        else showEm = "??, ";
        for (int i = 1; i < stack.size()-1; i++){
            if(stack.get(i).isFaceUp()){
                showEm += stack.get(i).getCard() + ", ";
            }
            else showEm += "??, ";
        }
        if (stack.size() != 1){
            showEm += stack.get(stack.size()-1).getCard();
        }
        System.out.println(showEm);
    }

    public void flipUp(){
        stack.get(stack.size()-1).flipUp();
    }

    public void removeFlipShow(){
        stack.remove(stack.size()-1);
        flipUp();
        showStack();
    }

    public void removeCard(){
        stack.remove(stack.size()-1);
    }

    public int getStackSize(){
        return stack.size();
    }

    public boolean isFaceUp(int index){
        return stack.get(index).isFaceUp();
    }

    public boolean addCheck(Card card){
        if (stack.size()==0 && card.getRank()==13){
            return true;
        }
        else if (stack.size()==0 && card.getRank()!=13){
            return false;
        }
        else if (stack.get(stack.size()-1).getRank() - 1 == card.getRank()){
            if (card.getSuit() == "\u0003" || card.getSuit() == "\u0004"){
                if (stack.get(stack.size()-1).getSuit() == "\u0005" || stack.get(stack.size()-1).getSuit() == "\u0006"){
                    return true;
                }
            }
            if (card.getSuit() == "\u0005" || card.getSuit() == "\u0006"){
                if (stack.get(stack.size()-1).getSuit() == "\u0003" || stack.get(stack.size()-1).getSuit() == "\u0004"){
                    return true;
                }
            }
        }
        return false;
    }

    public Card grabFullCard(int index){
        return stack.get(index);
    }

    public int howManyFaces(){
        int sum = 0;
        for (int i = stack.size()-1; i >= 0; i--){
            if (isFaceUp(i)){
                sum++;
            }
            else break;
        }
        return sum;
    }
}

//Dealing all of the cards out
//Figuring out how the stock works
// ****Printing everything out properly
//USER INPUT