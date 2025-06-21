package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        Collections.shuffle(cards);
    }

    private void initializeDeck() {
        cards.add(new PokemonCard("Togekiss", 100, "resources/Togekiss.png"));
        cards.add(new PokemonCard("Espeon", 140, "resources/Espeon.png"));
        cards.add(new PokemonCard("Alomomola", 150, "resources/Alomomola.png"));
        cards.add(new PokemonCard("Flittle", 20, "resources/Flittle.png"));
        cards.add(new PokemonCard("Banette", 120, "resources/Banette.png"));
        cards.add(new PokemonCard("Flabebe", 20, "resources/Flabebe.png"));
        cards.add(new PokemonCard("Vivillon", 110, "resources/Vivillon.png"));
        cards.add(new PokemonCard("Scrovillain", 110, "resources/Scrovillain.png"));
        cards.add(new PokemonCard("Arcanine", 280, "resources/Arcanine.png"));
        cards.add(new PokemonCard("Quaquaval", 140, "resources/Quaquaval.png"));
        // Deste 10 kartla sınırlı olduğu için TrainerCard'ları eklemiyoruz
    }

    public Card drawCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    public int getRemainingCards() {
        return cards.size();
    }
}
