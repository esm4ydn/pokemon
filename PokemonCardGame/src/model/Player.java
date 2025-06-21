package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Card> hand;
    private int score;

    public Player() {
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public void showHand() {
        for (Card card : hand) {
            System.out.println(card);
        }
    }
}
