package ui;

import game.Game;
import model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameUI extends JFrame {
    private Game playerVsComputerGame;
    private Game computerVsComputerGame;
    private static final int CARD_WIDTH = 155;
    private static final int DECK_WIDTH = 250;

    private JLabel playerScoreLabel;
    private JLabel computerScoreLabel;
    private JLabel computer1ScoreLabel;
    private JLabel computer2ScoreLabel;
    private JLabel roundResultLabel1;
    private JLabel roundResultLabel2;
    private JLabel deckLabel1;
    private JLabel deckLabel2;
    private JPanel playerPanel;
    private JPanel computerPanel;
    private JPanel computer1Panel;
    private JPanel computer2Panel;
    private JPanel deckPanel1;
    private JPanel deckPanel2;

    public GameUI(boolean isComputerVsComputerMode) {
        if (isComputerVsComputerMode) {
            computerVsComputerGame = new Game(true);
            setupComputerVsComputerPanel();
            computerVsComputerGame.startGame();
        } else {
            playerVsComputerGame = new Game(false);
            setupPlayerVsComputerPanel();
            playerVsComputerGame.startGame();
        }
        setVisible(true);
    }

    private void setupPlayerVsComputerPanel() {
        setTitle("Pokemon Card Game");
        setSize(1424, 818);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Arka plan paneli
        BackgroundPanelGame mainPanelGame = new BackgroundPanelGame("resources/game_wallpaper_1024x768.png");
        mainPanelGame.setLayout(new BorderLayout());

        // Oyun modu etiketi
        JLabel modeLabel = new JLabel("Oyuncu vs Bilgisayar");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanelGame.add(modeLabel, BorderLayout.NORTH);

        // Oyuncu ve bilgisayar panelleri
        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Player"));
        playerPanel.setOpaque(false); // Şeffaflık için
        mainPanelGame.add(playerPanel, BorderLayout.WEST);

        computerPanel = new JPanel();
        computerPanel.setBorder(BorderFactory.createTitledBorder("Computer"));
        computerPanel.setOpaque(false); // Şeffaflık için
        mainPanelGame.add(computerPanel, BorderLayout.EAST);

        // Kart destesi paneli
        deckPanel1 = new JPanel();
        deckPanel1.setOpaque(false);
        mainPanelGame.add(deckPanel1, BorderLayout.CENTER);

        // Skor panelleri
        playerScoreLabel = new JLabel("Player Score: 0");
        computerScoreLabel = new JLabel("Computer Score: 0");

        // Tur sonucu etiketi
        roundResultLabel1 = new JLabel("");
        roundResultLabel1.setFont(new Font("Arial", Font.BOLD, 24));
        roundResultLabel1.setForeground(Color.YELLOW);
        roundResultLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanelGame.add(roundResultLabel1, BorderLayout.NORTH);

        // Tur oynama butonu
        JButton playRoundButton = new JButton("Play Round");
        playRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = playerVsComputerGame.playRound();
                roundResultLabel1.setText(result);
                updateScores();
                updateHands();
                displayDeck(deckPanel1, playerVsComputerGame);

                if (playerVsComputerGame.isDeckEmpty() && playerVsComputerGame.getPlayerHand().getHandCardCount() == 0 && playerVsComputerGame.getComputerHand().getHandCardCount() == 0) {
                    JOptionPane.showMessageDialog(null, playerVsComputerGame.getGameResult(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    playerVsComputerGame.resetTurn();
                }
            }
        });

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.setOpaque(false);
        playButtonPanel.add(playRoundButton);

        // Skor ve buton panellerini aynı satıra ekleme
        JPanel scoreAndButtonPanel = new JPanel();
        scoreAndButtonPanel.setLayout(new BorderLayout());
        scoreAndButtonPanel.add(playerScoreLabel, BorderLayout.WEST);
        scoreAndButtonPanel.add(playButtonPanel, BorderLayout.CENTER);
        scoreAndButtonPanel.add(computerScoreLabel, BorderLayout.EAST);

        mainPanelGame.add(scoreAndButtonPanel, BorderLayout.SOUTH);

        // Deste paneli
        deckLabel1 = new JLabel();
        deckLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        deckLabel1.setIcon(new ImageIcon(new ImageIcon("resources/Pokemon_kart_deste.png").getImage().getScaledInstance(DECK_WIDTH, -1, Image.SCALE_SMOOTH)));
        deckLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (playerVsComputerGame.isPlayerTurnToDraw()) {
                    playerVsComputerGame.drawCardForPlayer();
                    updateHands();
                    if (!playerVsComputerGame.isComputerTurnToDraw()) {
                        playerVsComputerGame.drawCardForComputer();
                        updateHands();
                    }
                } else if (playerVsComputerGame.isComputerTurnToDraw()) {
                    playerVsComputerGame.drawCardForComputer();
                    updateHands();
                }
                displayDeck(deckPanel1, playerVsComputerGame);
            }
        });
        deckPanel1.add(deckLabel1);
        add(mainPanelGame, BorderLayout.CENTER);

        // Kartları görüntüleme
        displayCards(playerPanel, playerVsComputerGame.getPlayerHand().getCards());
        displayCards(computerPanel, playerVsComputerGame.getComputerHand().getCards());
        displayDeck(deckPanel1, playerVsComputerGame);
    }

    private void setupComputerVsComputerPanel() {
        setTitle("Pokemon Card Game");
        setSize(1424, 818);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Arka plan paneli
        BackgroundPanelGame mainPanelGame = new BackgroundPanelGame("resources/game_wallpaper_1024x768.png");
        mainPanelGame.setLayout(new BorderLayout());

        // Oyun modu etiketi
        JLabel modeLabel = new JLabel("Bilgisayar vs Bilgisayar");
        modeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        modeLabel.setForeground(Color.WHITE);
        modeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanelGame.add(modeLabel, BorderLayout.NORTH);

        // Bilgisayar panelleri
        computer1Panel = new JPanel();
        computer1Panel.setBorder(BorderFactory.createTitledBorder("Computer 1"));
        computer1Panel.setOpaque(false); // Şeffaflık için
        mainPanelGame.add(computer1Panel, BorderLayout.WEST);

        computer2Panel = new JPanel();
        computer2Panel.setBorder(BorderFactory.createTitledBorder("Computer 2"));
        computer2Panel.setOpaque(false); // Şeffaflık için
        mainPanelGame.add(computer2Panel, BorderLayout.EAST);

        // Kart destesi paneli
        deckPanel2 = new JPanel();
        deckPanel2.setOpaque(false);
        mainPanelGame.add(deckPanel2, BorderLayout.CENTER);

        // Skor panelleri
        computer1ScoreLabel = new JLabel("Computer 1 Score: 0");
        computer2ScoreLabel = new JLabel("Computer 2 Score: 0");

        // Tur sonucu etiketi
        roundResultLabel2 = new JLabel("");
        roundResultLabel2.setFont(new Font("Arial", Font.BOLD, 24));
        roundResultLabel2.setForeground(Color.YELLOW);
        roundResultLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanelGame.add(roundResultLabel2, BorderLayout.NORTH);

        // Tur oynama butonu
        JButton playRoundButton = new JButton("Play Round");
        playRoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String result = computerVsComputerGame.playRound();
                roundResultLabel2.setText(result);
                updateScores();
                updateHands();
                displayDeck(deckPanel2, computerVsComputerGame);

                if (computerVsComputerGame.isDeckEmpty() && computerVsComputerGame.getComputer1Hand().getHandCardCount() == 0 && computerVsComputerGame.getComputer2Hand().getHandCardCount() == 0) {
                    JOptionPane.showMessageDialog(null, computerVsComputerGame.getGameResult(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    computerVsComputerGame.resetTurn();
                    computerVsComputerGame.drawCardForComputer1();
                    computerVsComputerGame.drawCardForComputer2();
                    updateHands();
                    displayDeck(deckPanel2, computerVsComputerGame);
                }
            }
        });

        JPanel playButtonPanel = new JPanel();
        playButtonPanel.setOpaque(false);
        playButtonPanel.add(playRoundButton);

        // Skor ve buton panellerini aynı satıra ekleme
        JPanel scoreAndButtonPanel = new JPanel();
        scoreAndButtonPanel.setLayout(new BorderLayout());
        scoreAndButtonPanel.add(computer1ScoreLabel, BorderLayout.WEST);
        scoreAndButtonPanel.add(playButtonPanel, BorderLayout.CENTER);
        scoreAndButtonPanel.add(computer2ScoreLabel, BorderLayout.EAST);

        mainPanelGame.add(scoreAndButtonPanel, BorderLayout.SOUTH);

        // Deste paneli
        deckLabel2 = new JLabel();
        deckLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        deckLabel2.setIcon(new ImageIcon(new ImageIcon("resources/Pokemon_kart_deste.png").getImage().getScaledInstance(DECK_WIDTH, -1, Image.SCALE_SMOOTH)));
        deckLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Bilgisayar vs Bilgisayar modunda mouse click olaylarını kullanmıyoruz
            }
        });
        deckPanel2.add(deckLabel2);
        add(mainPanelGame, BorderLayout.CENTER);

        // Kartları görüntüleme
        displayCards(computer1Panel, computerVsComputerGame.getComputer1Hand().getCards());
        displayCards(computer2Panel, computerVsComputerGame.getComputer2Hand().getCards());
        displayDeck(deckPanel2, computerVsComputerGame);
    }

    private void displayCards(JPanel panel, Card[] cards) {
        panel.removeAll();
        panel.setLayout(new GridLayout(0, 1)); // Kartların tam olarak görünmesini sağlamak için satır sayısını dinamik olarak belirleyelim
        for (Card card : cards) {
            if (card != null) {
                JLabel cardLabel = new JLabel(card.getResizedImage(CARD_WIDTH));
                panel.add(cardLabel);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void displayDeck(JPanel panel, Game game) {
        panel.removeAll(); // Paneli temizle
        if (game.getDeck().getRemainingCards() > 0) {
            ImageIcon originalIcon = new ImageIcon("resources/Pokemon_kart_deste.png");
            Image image = originalIcon.getImage();
            Image resizedImage = image.getScaledInstance(DECK_WIDTH, -1, Image.SCALE_SMOOTH);
            if (game.equals(playerVsComputerGame)) {
                deckLabel1.setIcon(new ImageIcon(resizedImage));
                panel.add(deckLabel1);
            } else {
                deckLabel2.setIcon(new ImageIcon(resizedImage));
                panel.add(deckLabel2);
            }
        } else {
            if (game.equals(playerVsComputerGame)) {
                deckLabel1.setIcon(null);
            } else {
                deckLabel2.setIcon(null);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    private void updateScores() {
        if (playerVsComputerGame != null) {
            playerScoreLabel.setText("Player Score: " + playerVsComputerGame.getPlayerScore());
            computerScoreLabel.setText("Computer Score: " + playerVsComputerGame.getComputerScore());
        }
        if (computerVsComputerGame != null) {
            computer1ScoreLabel.setText("Computer 1 Score: " + computerVsComputerGame.getComputer1Score());
            computer2ScoreLabel.setText("Computer 2 Score: " + computerVsComputerGame.getComputer2Score());
        }
    }

    private void updateHands() {
        if (playerVsComputerGame != null) {
            displayCards(playerPanel, playerVsComputerGame.getPlayerHand().getCards());
            displayCards(computerPanel, playerVsComputerGame.getComputerHand().getCards());
        }
        if (computerVsComputerGame != null) {
            displayCards(computer1Panel, computerVsComputerGame.getComputer1Hand().getCards());
            displayCards(computer2Panel, computerVsComputerGame.getComputer2Hand().getCards());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerSelectionUI();
            }
        });
    }
}

// Arka plan paneli sınıfı
class BackgroundPanelGame extends JPanel {
    private Image backgroundImage;

    public BackgroundPanelGame(String fileName) {
        try {
            backgroundImage = new ImageIcon(fileName).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
