package game;

import model.Card;
import model.Hand;
import model.Deck;

public class Game {
    private Deck deck;
    private Hand playerHand;
    private Hand computerHand;
    private int playerScore;
    private int computerScore;
    private boolean playerTurnToDraw;
    private boolean computerTurnToDraw;
    private boolean computerVsComputerMode;
    private Hand computer1Hand;
    private Hand computer2Hand;
    private int computer1Score;
    private int computer2Score;

    public Game(boolean computerVsComputerMode) {
        deck = new Deck();
        if (computerVsComputerMode) {
            computer1Hand = new Hand(3);
            computer2Hand = new Hand(3);
        } else {
            playerHand = new Hand(3);
            computerHand = new Hand(3);
        }
        this.computerVsComputerMode = computerVsComputerMode;
        playerScore = 0;
        computerScore = 0;
        computer1Score = 0;
        computer2Score = 0;
        playerTurnToDraw = true;
        computerTurnToDraw = true;
        dealCards(); // Kartları başlatma işlemi burada yapılır
    }

    private void dealCards() {
        if (computerVsComputerMode) {
            // Bilgisayar vs Bilgisayar modunda 3'er kart dağıt
            for (int i = 0; i < 3; i++) {
                if (deck.getRemainingCards() > 0) {
                    computer1Hand.addCard(deck.drawCard());
                }
                if (deck.getRemainingCards() > 0) {
                    computer2Hand.addCard(deck.drawCard());
                }
            }
        } else {
            // Oyuncu vs Bilgisayar modunda 3'er kart dağıt
            for (int i = 0; i < 3; i++) {
                if (deck.getRemainingCards() > 0) {
                    playerHand.addCard(deck.drawCard());
                }
                if (deck.getRemainingCards() > 0) {
                    computerHand.addCard(deck.drawCard());
                }
            }
        }

        // Kartların doğru dağıtıldığını kontrol et
        System.out.println("Player Hand: " + (playerHand != null ? playerHand.getHandCardCount() : "N/A"));
        System.out.println("Computer Hand: " + (computerHand != null ? computerHand.getHandCardCount() : "N/A"));
        System.out.println("Computer1 Hand: " + (computer1Hand != null ? computer1Hand.getHandCardCount() : "N/A"));
        System.out.println("Computer2 Hand: " + (computer2Hand != null ? computer2Hand.getHandCardCount() : "N/A"));
        System.out.println("Remaining Cards in Deck: " + deck.getRemainingCards());
    }

    public void startGame() {
        System.out.println("Game started!");
        if (computerVsComputerMode) {
            System.out.println("Computer 1 Hand: " + computer1Hand);
            System.out.println("Computer 2 Hand: " + computer2Hand);
        } else {
            System.out.println("Player Hand: " + playerHand);
            System.out.println("Computer Hand: " + computerHand);
        }
    }

    public String playRound() {
        if (computerVsComputerMode) {
            if (computer1Hand.getHandCardCount() > 0 && computer2Hand.getHandCardCount() > 0) {
                Card computer1Card = computer1Hand.getCards()[computer1Hand.getHandCardCount() - 1];
                Card computer2Card = computer2Hand.getCards()[computer2Hand.getHandCardCount() - 1];

                System.out.println("Computer 1 plays: " + computer1Card);
                System.out.println("Computer 2 plays: " + computer2Card);

                String result;
                if (computer1Card.getDamage() > computer2Card.getDamage()) {
                    computer1Score += 5;
                    result = "Computer 1 wins the round!";
                } else if (computer1Card.getDamage() < computer2Card.getDamage()) {
                    computer2Score += 5;
                    result = "Computer 2 wins the round!";
                } else {
                    result = "It's a tie!";
                }

                computer1Hand.removeLastCard();
                computer2Hand.removeLastCard();

                // Her iki bilgisayar da desteden bir kart çeker
                if (deck.getRemainingCards() > 0) {
                    if (computer1Hand.getHandCardCount() < 3) {
                        computer1Hand.addCard(deck.drawCard());
                    }
                    if (computer2Hand.getHandCardCount() < 3) {
                        computer2Hand.addCard(deck.drawCard());
                    }
                }

                return result;
            }
        } else {
            if (playerHand.getHandCardCount() > 0 && computerHand.getHandCardCount() > 0) {
                Card playerCard = playerHand.getCards()[playerHand.getHandCardCount() - 1];
                Card computerCard = computerHand.getCards()[computerHand.getHandCardCount() - 1];

                System.out.println("Player plays: " + playerCard);
                System.out.println("Computer plays: " + computerCard);

                String result;
                if (playerCard.getDamage() > computerCard.getDamage()) {
                    playerScore += 5;
                    result = "Player wins the round!";
                } else if (playerCard.getDamage() < computerCard.getDamage()) {
                    computerScore += 5;
                    result = "Computer wins the round!";
                } else {
                    result = "It's a tie!";
                }

                playerHand.removeLastCard();
                computerHand.removeLastCard();

                // Her iki oyuncu da desteden bir kart çeker
                if (deck.getRemainingCards() > 0) {
                    if (playerHand.getHandCardCount() < 3) {
                        playerHand.addCard(deck.drawCard());
                    }
                    if (computerHand.getHandCardCount() < 3) {
                        computerHand.addCard(deck.drawCard());
                    }
                }

                return result;
            }
        }
        return "No more cards to play.";
    }

    public boolean drawCardForPlayer() {
        if (playerTurnToDraw && deck.getRemainingCards() > 0) {
            playerHand.addCard(deck.drawCard());
            playerTurnToDraw = false;
            return true;
        }
        return false;
    }

    public boolean drawCardForComputer() {
        if (computerTurnToDraw && deck.getRemainingCards() > 0) {
            computerHand.addCard(deck.drawCard());
            computerTurnToDraw = false;
            return true;
        }
        return false;
    }

    public boolean drawCardForComputer1() {
        if (computerTurnToDraw && deck.getRemainingCards() > 0) {
            computer1Hand.addCard(deck.drawCard());
            computerTurnToDraw = false;
            return true;
        }
        return false;
    }

    public boolean drawCardForComputer2() {
        if (computerTurnToDraw && deck.getRemainingCards() > 0) {
            computer2Hand.addCard(deck.drawCard());
            computerTurnToDraw = false;
            return true;
        }
        return false;
    }

    public void resetTurn() {
        playerTurnToDraw = true;
        computerTurnToDraw = true;
    }

    public boolean isDeckEmpty() {
        return deck.getRemainingCards() == 0;
    }

    public boolean isPlayerTurnToDraw() {
        return playerTurnToDraw;
    }

    public boolean isComputerTurnToDraw() {
        return computerTurnToDraw;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public Hand getComputerHand() {
        return computerHand;
    }

    public Hand getComputer1Hand() {
        return computer1Hand;
    }

    public Hand getComputer2Hand() {
        return computer2Hand;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getComputerScore() {
        return computerScore;
    }

    public int getComputer1Score() {
        return computer1Score;
    }

    public int getComputer2Score() {
        return computer2Score;
    }

    public String getGameResult() {
        if (computerVsComputerMode) {
            if (computer1Score > computer2Score) {
                return "Computer 1 wins with " + computer1Score + " points!";
            } else if (computer1Score < computer2Score) {
                return "Computer 2 wins with " + computer2Score + " points!";
            } else {
                return "It's a tie with both computers having " + computer1Score + " points!";
            }
        } else {
            if (playerScore > computerScore) {
                return "Player wins with " + playerScore + " points!";
            } else if (playerScore < computerScore) {
                return "Computer wins with " + computerScore + " points!";
            } else {
                return "It's a tie with both players having " + playerScore + " points!";
            }
        }
    }

    public boolean isComputerVsComputerMode() {
        return computerVsComputerMode;
    }
}
