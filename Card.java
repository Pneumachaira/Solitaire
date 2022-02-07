import java.util.HashMap;

public class Card {

	private String suit;
	private int rank;
	private String name;
	private boolean faceUp = false;

	public Card(String suit, Integer rank) {
		this.suit = suit;
		this.rank = rank;
		this.name = "";

		HashMap<Integer, String> names = new HashMap<Integer, String>();
		names.put(1, "A");
		names.put(10, "X");
		names.put(11, "J");
		names.put(12, "Q");
		names.put(13, "K");
			
			
		if (names.get(rank) != null) {
			this.name = names.get(rank);
		}
		else {
			this.name = Integer.toString(rank);
		}
	}

	public String getCard() {
		return this.name + this.suit;
	}

	public void showCard() {
		System.out.printf("%s of %s\n", this.name, this.suit);
	}


	public String getSuit() {
		return suit;
	}

	public boolean isFaceUp(){
		return faceUp;
	}


	public void setSuit(String suit) {
		this.suit = suit;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
    
	public void flipUp(){
		this.faceUp = true;
	}
    
}
