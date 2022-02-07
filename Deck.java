import java.util.ArrayList;
import java.util.Random;

public class Deck {

    Random randMachine = new Random();
	
	private ArrayList<Card> cards;
    private ArrayList<Tableau> tabs = new ArrayList<Tableau>();
	private ArrayList<Card> stock = new ArrayList<Card>();
    private ArrayList<Foundation> founds = new ArrayList<Foundation>();
    private int stockMarker = 23;
    private boolean game = true;
    private String playTop = "[p] play top card";
    private String blankTop = "                 ";
    private String nextStock = "[n] next stock";
    private String blankStock = "              ";
    private String playFoundation = "[f] play on foundation";

    public Deck() {
        this.cards = new ArrayList<Card>();

        // Populate the cards list -- loop to 52
        for (String name : new String[] {"\u0003", "\u0005", "\u0004", "\u0006"}) { // Hearts, clubs, diamonds, spades
            for (Integer rank = 1; rank <= 13; rank++) {
                this.cards.add(new Card(name, rank));
            }
        }
    }

	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

    public void shuffleCards() {
        for (int i = 0; i < 500; i++){
            int firstIndex = randMachine.nextInt(cards.size());
            int secondIndex = randMachine.nextInt(cards.size());
            Card temp = cards.get(firstIndex);
            cards.set(firstIndex, cards.get(secondIndex));
            cards.set(secondIndex, temp);
        }
    }

    public void showDeck(){
        String showEm = cards.get(0).getCard() +", ";
        for (int i = 1; i < cards.size()-1; i++){
            showEm += cards.get(i).getCard() + ", ";
        }
        showEm += cards.get(cards.size()-1).getCard();
        System.out.println(showEm);
    }

    public void addTab(Tableau tab){
        tabs.add(tab);
    }

    public void dealTabs(){
        for (int i=0; i<7; i++){
            for(int j=i; j<7; j++){
                tabs.get(j).addCard(cards.get(cards.size()-1));
                if (i == j){
                    tabs.get(j).flipUp();
                } //tabs>tab[0].get([tab[0].size()-1])
            cards.remove(cards.size()-1);
            }
        }
    }

    public void dealStock(){
        while(stock.size() != 0){
            stock.remove(0);
        }
        if (cards.size() >= 3) {
            while(stock.size() < 3){
                stock.add(cards.get(stockMarker));
                if (stockMarker == 0){
                    stockMarker = cards.size()-1;
                }
                else stockMarker -= 1;
            }
            // System.out.println(stock.get(0).getCard() + ", " + stock.get(1).getCard() + ", " + stock.get(2).getCard());
        }
        else if (cards.size() == 2){
            stock.add(cards.get(1));
            stock.add(cards.get(0));
            // System.out.println(stock.get(0).getCard() + ", " + stock.get(1).getCard());
        }
        else if (cards.size() == 1){
            stock.add(cards.get(0));
            // System.out.println(stock.get(0).getCard());
        }
        // else System.out.println("Empty!");
    }

    // public void removeCard(){
    //     cards.remove(cards.size()-1);
    //     if (stockMarker == 0){
    //         stockMarker = cards.size()-1;
    //     }
    //     else stockMarker -= 1;
    // }

    public void playStock(){
        while (stockMarker >= cards.size()){
            stockMarker--;
        }
        if (cards.size() <= 3 && stock.size()==cards.size()){
            cards.remove(0);
            stock.remove(stock.size()-1);
        }
        else {
            stock.remove(stock.size()-1);
            if (stockMarker == cards.size()-1){
                cards.remove(0);
                stockMarker--;
            }
            else cards.remove(stockMarker + 1);
        }
    }

    public void showStock(){
        if (stock.size() == 3){
            System.out.println(stock.get(0).getCard() + ", " + stock.get(1).getCard() + ", " + stock.get(2).getCard());
        }
        else if (stock.size() == 2){
            System.out.println(stock.get(0).getCard() + ", " + stock.get(1).getCard());
        }
        else if (stock.size() == 1){
            System.out.println(stock.get(0).getCard());
        }
        else if (stock.size() == 3){
            System.out.println(stock.get(0).getCard());
        }
        else System.out.println("It's empty!");
    }

    public void mainDisplay(String input1, String input2, String input3, String input4){
        String topHalf = cards.size() + " cards remaining in deck\n";
        if (stock.size()==3){ //25 spaces counting the |'s, displaying the tops
            topHalf += " __ __ ____                             ____  ____  ____  ____\n";
            topHalf += "|" + stock.get(0).getCard() + "|" + stock.get(1).getCard() + "|" + stock.get(2).getCard() + "  |                           |";
        }
        else if (stock.size()==2){ //28 spaces
            topHalf += " __ ____                                ____  ____  ____  ____\n";
            topHalf += "|" + stock.get(0).getCard() + "|" + stock.get(1).getCard() + "  |                              |";
            
        }
        else if (stock.size()==1){ //31 spaces
            topHalf += " ____                                   ____  ____  ____  ____\n";
            topHalf += "|" + stock.get(0).getCard() + "  |                                 |";
        }
        else if (stock.size()==0){
            topHalf += "                                        ____  ____  ____  ____\n";
            topHalf += "                                       |";
        }

        //Displaying the foundations
        if (founds.get(0).isEmpty()){
            topHalf += " \u0003  ||";
        }
        else {
            topHalf += founds.get(0).topCard() + "  ||";
        }
        if (founds.get(1).isEmpty()){
            topHalf += " \u0004  ||";
        }
        else {
            topHalf += founds.get(1).topCard() + "  ||";
        }
        if (founds.get(2).isEmpty()){
            topHalf += " \u0005  ||";
        }
        else {
            topHalf += founds.get(2).topCard() + "  ||";
        }
        if (founds.get(3).isEmpty()){
            topHalf += " \u0006  |\n";
        }
        else {
            topHalf += founds.get(3).topCard() + "  |\n";
        }
        // And back to the stock, gotta make sure to see how many are left
        if (stock.size()==3){
            topHalf += "|  |  |    | " + input3 + "         |    ||    ||    ||    | " + input1 + "\n";
            topHalf += "|__|__|____| " + input4 + "            |____||____||____||____|\n";
        }
        else if (stock.size()==2){
            topHalf += "|  |    |    " + input3 + "         |    ||    ||    ||    | " + input1 + "\n";
            topHalf += "|__|____|    " + input4 + "            |____||____||____||____|\n";
        }
        else if (stock.size()==1){
            topHalf += "|    |       " + input3 + "         |    ||    ||    ||    | " + input1 + "\n";
            topHalf += "|____|       " + input4 + "            |____||____||____||____|\n";
        }
        else if (stock.size()==0 && cards.size() == 0){
            topHalf += "                                       |    ||    ||    ||    | " + input1 + "\n";
            topHalf += "             [] no cards remaining     |____||____||____||____|\n";
        }
        else if (stock.size()==0){
            topHalf += "                                       |    ||    ||    ||    | " + input1 + "\n";
            topHalf += "             " + input4 + "            |____||____||____||____|\n";
        }
        topHalf += "\n [1]   [2]   [3]   [4]   [5]   [6]   [7]   " + input2 + "\n ____  ____  ____  ____  ____  ____  ____";
        System.out.println(topHalf);
        String botHalf = "";
        int max = maxStack();
        for (int i = 1; i <= max; i++){ // Displaying the tableaus
            for (int k = 0; k < 2; k++){
                for (int j = 0; j < 7; j++){
                    if (tabs.get(j).getStackSize() >= i && k==0){
                        if (tabs.get(j).isFaceUp(i-1)){
                            botHalf += "|" + tabs.get(j).grabOne(i-1) + "  |";
                        }
                        else botHalf += "|    |";
                    }
                    else if (tabs.get(j).getStackSize() > i && k == 1){
                        botHalf += "|____|";
                    }
                    else if (tabs.get(j).getStackSize() == i && k == 1){
                        botHalf += "|    |";
                    }
                    else if (tabs.get(j).getStackSize() == i-1 && k == 0){
                        botHalf += "|____|";
                    }
                    else botHalf += "      ";

                }
                botHalf += "\n";
            }
        }
        for (int i = 0; i < tabs.size(); i++){ //Closing up the leftovers
            if (tabs.get(i).getStackSize() == max){
                botHalf += "|____|";
            }
            else botHalf += "      ";
        }
        System.out.println(botHalf);
    }

    public void addFounds(Foundation found){
        founds.add(found);
        System.out.println(founds.size() +", most recent is " + founds.get(founds.size()-1).getSuit());
    }

    public int maxStack(){
        int max = 0;
        for (int i = 0; i < 7; i++){
            if (tabs.get(i).getStackSize() > max){
                max = tabs.get(i).getStackSize();
            }
        }
        return max;
    }

    public void tabPlayOne(Tableau ourTab){
        String cardName = "";
        if(ourTab.getStackSize() != 0){
            cardName = ourTab.grabFullCard(ourTab.getStackSize()-1).getCard();
        }
        if(ourTab.getStackSize() == 0){
            mainDisplay(" ", "That column is empty. Please make another selection.", playTop, nextStock);
        }
        else if (!ourTab.isFaceUp(ourTab.getStackSize()-1)){
            ourTab.flipUp();
            mainDisplay("", "Card has been flipped. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if(ourTab.howManyFaces() == 1){
            mainDisplay(playFoundation, "Where would you like to place " + cardName + "?", blankTop, blankStock);
            tabSinglePlayTwo(ourTab, ourTab.grabFullCard(ourTab.getStackSize()-1));
        }
        else {
            mainDisplay("", "How many cards from this column would you like to move?", blankTop, blankStock);
            String input = System.console().readLine();
            if (input.equals("1")){
                mainDisplay(playFoundation, "Where would you like to place " + cardName + "?", blankTop, blankStock);
                tabSinglePlayTwo(ourTab, ourTab.grabFullCard(ourTab.getStackSize()-1));
            }
            else if (input.equals("2")){
                tabMultiPlayTwo(ourTab, 2);
            }
            else if (input.equals("3")){
                tabMultiPlayTwo(ourTab, 3);
            }
            else if (input.equals("4")){
                tabMultiPlayTwo(ourTab, 4);
            }
            else if (input.equals("5")){
                tabMultiPlayTwo(ourTab, 5);
            }
            else if (input.equals("6")){
                tabMultiPlayTwo(ourTab, 6);
            }
            else if (input.equals("7")){
                tabMultiPlayTwo(ourTab, 7);
            }
            else if (input.equals("8")){
                tabMultiPlayTwo(ourTab, 8);
            }
            else if (input.equals("9")){
                tabMultiPlayTwo(ourTab, 9);
            }
            else if (input.equals("10")){
                tabMultiPlayTwo(ourTab, 10);
            }
            else if (input.equals("11")){
                tabMultiPlayTwo(ourTab, 11);
            }
            else if (input.equals("12")){
                tabMultiPlayTwo(ourTab, 12);
            }
            else if (input.equals("13")){
                tabMultiPlayTwo(ourTab, 13);
            }
            else if (input.equals("f")){
                Card card = ourTab.grabFullCard(ourTab.getStackSize()-1);
                if (card.getSuit() == "\u0003"){
                    addFoundationAttempt(founds.get(0), card, ourTab);
                }
                else if (card.getSuit() == "\u0004"){
                    addFoundationAttempt(founds.get(1), card, ourTab);
                }
                else if (card.getSuit() == "\u0005"){
                    addFoundationAttempt(founds.get(2), card, ourTab);
                }
                else addFoundationAttempt(founds.get(3), card, ourTab);
            }
            else mainDisplay("", "Invalid selection. Type a bracketed character to continue.", playTop, nextStock);
        }
    }

    public void tabMultiPlayTwo(Tableau ourTab, int amount){
        if (amount <= ourTab.howManyFaces()){
            Card card = ourTab.grabFullCard(ourTab.getStackSize()-amount);
            mainDisplay("", "Select where to move the stack headed by " + card.getCard(), blankTop, blankStock);
            tabMultiPlayThree(ourTab, amount, card);
        }
        else mainDisplay("", "There aren't that many cards. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void tabMultiPlayThree(Tableau ourTab, int amount, Card card){
        String input = System.console().readLine();
        if (input.equals("1")){
            if (tabs.get(0).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(0).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("2")){
            if (tabs.get(1).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(1).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("3")){
            if (tabs.get(2).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(2).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("4")){
            if (tabs.get(3).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(3).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("5")){
            if (tabs.get(4).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(4).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("6")){
            if (tabs.get(5).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(5).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
        }
        else if (input.equals("7")){
            if (tabs.get(6).addCheck(card)){
                for (int i = amount; i > 0; i--){
                    tabs.get(6).addCard(ourTab.grabFullCard(ourTab.getStackSize()-i));
                }
                for (int i = 0; i < amount; i++){
                    ourTab.removeCard();
                }
                mainDisplay("", "Cards moved. Type a bracketed character to continue.", playTop, nextStock);
            }
            else mainDisplay("", "Invalid selection. Type a bracketed character to continue.", playTop, nextStock);
        }

    }

    public void tabSinglePlayTwo(Tableau ourTab, Card card){
        String input = System.console().readLine();
        if (input.equals("1")){
            addTabAttempt(ourTab, tabs.get(0), card);
        }
        else if (input.equals("2")){
            addTabAttempt(ourTab, tabs.get(1), card);
        }
        else if (input.equals("3")){
            addTabAttempt(ourTab, tabs.get(2), card);
        }
        else if (input.equals("4")){
            addTabAttempt(ourTab, tabs.get(3), card);
        }
        else if (input.equals("5")){
            addTabAttempt(ourTab, tabs.get(4), card);
        }
        else if (input.equals("6")){
            addTabAttempt(ourTab, tabs.get(5), card);
        }
        else if (input.equals("7")){
            addTabAttempt(ourTab, tabs.get(6), card);
        }
        else if (input.equals("f")){
            if (card.getSuit() == "\u0003"){
                addFoundationAttempt(founds.get(0), card, ourTab);
            }
            else if (card.getSuit() == "\u0004"){
                addFoundationAttempt(founds.get(1), card, ourTab);
            }
            else if (card.getSuit() == "\u0005"){
                addFoundationAttempt(founds.get(2), card, ourTab);
            }
            else addFoundationAttempt(founds.get(3), card, ourTab);
        }
    }

    public void addFoundationAttempt(Foundation found, Card card, Tableau ourTab){ //Overload to play from tabs
        if (found.addCheck(card)){
            ourTab.removeCard();
            found.addCard(card);
            mainDisplay("", "Nice work! Type a bracketed character to continue.", playTop, nextStock);
            if (found.getSize()==13){
                checkVictory();
            }
        }
        else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void addFoundationAttempt(Foundation found, Card card){ //For playing from the stock
        if (found.addCheck(card)){
            playStock();
            found.addCard(card);
            mainDisplay("", "Nice work! Type a bracketed character to continue.", playTop, nextStock);
            if (found.getSize()==13){
                checkVictory();
            }
        }
        else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void checkVictory(){
        boolean victory = true;
        for (int i = 0; i < 4; i++){
            if (founds.get(i).getSize() != 13){
                victory = false;
            }
        }
        if (victory) {
            game = false;
        }
    }

    public void playTopStock(Card card){
        String cardName = card.getCard();
        mainDisplay(playFoundation, "Where would you like to place " + cardName + "?", blankTop, blankStock);
        String input = System.console().readLine();
        if (input.equals("1")){
            addTabAttempt(tabs.get(0), card);
        }
        else if (input.equals("2")){
            addTabAttempt(tabs.get(1), card);
        }
        else if (input.equals("3")){
            addTabAttempt(tabs.get(2), card);
        }
        else if (input.equals("4")){
            addTabAttempt(tabs.get(3), card);
        }
        else if (input.equals("5")){
            addTabAttempt(tabs.get(4), card);
        }
        else if (input.equals("6")){
            addTabAttempt(tabs.get(5), card);
        }
        else if (input.equals("7")){
            addTabAttempt(tabs.get(6), card);
        }
        else if (input.equals("f")){
            if (card.getSuit()=="\u0003"){
                addFoundationAttempt(founds.get(0), card);
            }
            else if (card.getSuit()=="\u0004"){
                addFoundationAttempt(founds.get(1), card);
            }
            else if (card.getSuit()=="\u0005"){
                addFoundationAttempt(founds.get(2), card);
            }
            else addFoundationAttempt(founds.get(3), card);
        }
        else mainDisplay("", "Invalid selection. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void addTabAttempt(Tableau otherTab, Card card){ //Used for playing from the stock
        if(otherTab.addCheck(card)){
            card.flipUp();
            otherTab.addCard(card);
            playStock();
            mainDisplay("", "Card moved. Type a bracketed character to continue.", playTop, nextStock);
        }
        else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void addTabAttempt(Tableau ourTab, Tableau otherTab, Card card){ //Used for moving from one tab to another
        if(otherTab.addCheck(card)){
            otherTab.addCard(card);
            ourTab.removeCard();
            mainDisplay("", "Card moved. Type a bracketed character to continue.", playTop, nextStock);
        }
        else mainDisplay("", "Invalid move. Type a bracketed character to continue.", playTop, nextStock);
    }

    public void gameStart(){ //Now we get to our game loop!
        while (game) {
            String input = System.console().readLine();
            if (input.equals("1")){
                tabPlayOne(tabs.get(0)); //Time to start making our lives easier!
            }
            else if (input.equals("2")){
                tabPlayOne(tabs.get(1));
            }
            else if (input.equals("3")){
                tabPlayOne(tabs.get(2));
            }
            else if (input.equals("4")){
                tabPlayOne(tabs.get(3));
            }
            else if (input.equals("5")){
                tabPlayOne(tabs.get(4));
            }
            else if (input.equals("6")){
                tabPlayOne(tabs.get(5));
            }
            else if (input.equals("7")){
                tabPlayOne(tabs.get(6));
            }
            else if (input.equals("p")){
                if (stock.size()==0 && cards.size() > 0){
                    mainDisplay("", "You should restock first. Type a bracketed character to continue.", blankTop, nextStock);
                }
                else if (cards.size()==0){
                    mainDisplay("", "All out, I'm afraid. Type a bracketed character to continue.", blankTop, blankStock);
                }
                else {
                    playTopStock(stock.get(stock.size()-1));
                }
            }
            else if (input.equals("n")){
                if(cards.size()==0){
                    mainDisplay("", "All out, I'm afraid. Type a bracketed character to continue.", blankTop, blankStock);
                }
                else{
                    dealStock();
                    mainDisplay("", "Nice and fresh. Type a bracketed character to continue.", playTop, nextStock);
                }
            }
            else mainDisplay("", "Invalid selection. Type a bracketed character to continue.", playTop, nextStock);
        }
        System.out.println("_________________________________________\n\n\u0003 \u0004 \u0005 \u0006 CONGRATULATIONS! YOU WIN! \u0003 \u0004 \u0005 \u0006\n_________________________________________");
    }
}
