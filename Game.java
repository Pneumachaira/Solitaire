public class Game{

    public static void main(String[] args){

        Deck theDeck = new Deck();
        theDeck.shuffleCards();

        Tableau tab0 = new Tableau();
        Tableau tab1 = new Tableau();
        Tableau tab2 = new Tableau();
        Tableau tab3 = new Tableau();
        Tableau tab4 = new Tableau();
        Tableau tab5 = new Tableau();
        Tableau tab6 = new Tableau();
        theDeck.addTab(tab0);
        theDeck.addTab(tab1);
        theDeck.addTab(tab2);
        theDeck.addTab(tab3);
        theDeck.addTab(tab4);
        theDeck.addTab(tab5);
        theDeck.addTab(tab6);
        theDeck.dealTabs();

        Foundation foundHearts = new Foundation("\u0003");
        Foundation foundDiamonds = new Foundation("\u0004");
        Foundation foundClubs = new Foundation("\u0005");
        Foundation foundSpades = new Foundation("\u0006");
        theDeck.addFounds(foundHearts);
        theDeck.addFounds(foundDiamonds);
        theDeck.addFounds(foundClubs);
        theDeck.addFounds(foundSpades);

        theDeck.dealStock();
        theDeck.mainDisplay(" ", "Welcome! Type a bracketed character to begin.", "[p] play top card", "[n] next stock");

        theDeck.gameStart();




        //What next...

        // System.out.println(" __ __ ____ \n|7\u0004|8\u0005|J\u0003  |\n|  |  |    | [p] play top card\n|__|__|____| [n] next");
        // System.out.println(" __ __ ____ \n|7\u0004|8\u0005|\u0003   |\n|  |  |    |\n|__|__|____|");
        // System.out.println("\n  [1]   [2]   [3]   [4]   [5]   [6]   [7]\n ____  ____  ____ \n|7\u0004  ||    ||J\u0003  |\n|    ||____||____|\n|____||8\u0006  ||10\u0005 | [h]\n      |    ||    |\n      |____||____||____||____||____||____|");
    }
}