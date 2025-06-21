package model;

public class Hand {
    private Card[] cards;
    private int sumCardCount;
    private int handCardCount;

    public Hand(int cardCount) {
        this.sumCardCount = cardCount;
        this.handCardCount = 0;
        cards = new Card[sumCardCount];
    }

    public void addCard(Card card) {
        if (handCardCount < sumCardCount) {
            cards[handCardCount] = card;
            handCardCount++;
            System.out.println("Added card: " + card.getName()); // Debug
        } else {
            System.out.println("Hand is full. Cannot add more cards.");
        }
    }

    public void removeLastCard() {
        if (handCardCount > 0) {
            handCardCount--;
            cards[handCardCount] = null; // Son kartı null olarak ayarlayarak çıkarıyoruz
        } else {
            System.out.println("Hand is empty. Cannot remove any card.");
        }
    }

    public void removeCard(int index) {
        if (index >= 0 && index < handCardCount) {
            for (int i = index; i < handCardCount - 1; i++) {
                cards[i] = cards[i + 1];
            }
            cards[handCardCount - 1] = null;
            handCardCount--;
        } else {
            System.out.println("Invalid index. Cannot remove card.");
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public int getHandCardCount() {
        return handCardCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Hand{");
        for (int i = 0; i < handCardCount; i++) {
            sb.append(cards[i].toString());
            if (i < handCardCount - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}

