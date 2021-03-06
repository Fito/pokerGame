package models;

public class Hand {
  private final static int MAX_SIZE = 5;
  private Card[] cards;

  public Hand() {
    this.cards = new Card[MAX_SIZE];
  }

	public Hand(Card[] cards) {
		this.cards = cards;
	}

  public Card[] getCards() {
    return cards;
  }
  /*public int size(Card[] cards){
		return cards.size();
	}*/

  public Card getCard(int index) {
    if ( invalidIndex(index) ) { return null; }
    return this.cards[index];
  }

  public void add(int index, Card card) {
    if ( invalidIndex(index) ) { return; }
    this.cards[index] = card;
  }

  public void remove(int index) {
    if ( invalidIndex(index) ) { return; }
    this.cards[index] = null;
  }

  public void replaceHighlighted(Deck currentDeck) {
    
    for( int x = 0; x < MAX_SIZE; x++ ) {
      if( cards[x].isHighlighted() ) { cards[x] = currentDeck.dealCard(); }
    }
  }

  public boolean isPresent(int rank, int suit) {
    for( int x = 0; x < MAX_SIZE; x++ ) {
      if( cards[x].getRank() == rank && cards[x].getSuit() == suit ) {
        return true;
      }
    }
    return false;
  }

  private boolean invalidIndex(int index) {
    return (index < 0 || index >= MAX_SIZE);
  }

}
